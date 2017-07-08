package com.manju.kramphub.task.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.manju.kramphub.task.callback.AppleSearchCallback;
import com.manju.kramphub.task.callback.GoogleSearchCallback;
import com.manju.kramphub.task.exceptions.KrampHubTaskException;
import com.manju.kramphub.task.model.ApiHealthStatus;
import com.manju.kramphub.task.model.ApiHealthStatus.ApiHealthStatusType;
import com.manju.kramphub.task.model.TaskResponse;

/**
 * Implementation of Kramp Hub Task service inferface.
 * 
 * @author manju
 * @version 1.0
 * @see com.manju.kramphub.task.services.KrampHubTaskService
 *
 */
@Service
public class KrampHubTaskServiceImpl implements KrampHubTaskService {
	
	private static final Logger LOG = LoggerFactory.getLogger(KrampHubTaskServiceImpl.class);
	
	@Autowired
	@Qualifier("googleBookSearchApi")
	private BaseSearchApi googleBookSearchApi;
	
	@Autowired
	@Qualifier("appleITunesSearchApi")
	private BaseSearchApi appleITunesSearchApi;
	
	private static final ExecutorService es  = Executors.newCachedThreadPool();

	/* (non-Javadoc)
	 * @see com.manju.kramphub.task.services.KrampHubTaskService#search(java.lang.String, int, com.manju.kramphub.task.model.HealthCheck)
	 */
	@Override
	public List<TaskResponse> search(String searchValue, int limit, ApiHealthStatus apiHealthStatus) throws KrampHubTaskException {
		List<TaskResponse> resultList = new ArrayList<>(limit);
		try {			
			//Running both the apple and google search api requests parallely.
			Future<Boolean> appleFuture = es.submit(new AppleSearchCallback(searchValue, limit, resultList, appleITunesSearchApi));
			Future<Boolean> googleFuture = es.submit(new GoogleSearchCallback(searchValue, limit, resultList, googleBookSearchApi));
			try {
				appleFuture.get(); //Waiting for the apple search api request to complete.
			} catch (InterruptedException | ExecutionException e) {
				LOG.error("Error while calling the apple itune search callback.", e);
				if(resultList.size() > 0) 
					apiHealthStatus.setAppleApiStatus(ApiHealthStatusType.PARTIALLY_PROCESSED);
				else 
					apiHealthStatus.setAppleApiStatus(ApiHealthStatusType.PROCESSING_ERROR);
			}
			try {
				googleFuture.get(); //Waiting for the google search api request to complete.
			} catch (InterruptedException | ExecutionException e) {
				LOG.error("Error while calling the google books search callback. ", e);
				if(resultList.size() > 0) 
					apiHealthStatus.setGoogleApiStatus(ApiHealthStatusType.PARTIALLY_PROCESSED);
				else 
					apiHealthStatus.setGoogleApiStatus(ApiHealthStatusType.PROCESSING_ERROR);
			}
			resultList.sort((r1, r2) -> r1.getTitle().compareTo(r2.getTitle()));
		} catch(RuntimeException e) {
			LOG.error("Error while calling search apis ", e);
			throw new KrampHubTaskException("Error while calling search apis.", e);
		}
		return resultList;
	}
	
	@PreDestroy
	public void closeExecutorService() { 
		if(es != null) {
			LOG.debug("Shutting down the executor service");
			es.shutdown();
		}
	}

}

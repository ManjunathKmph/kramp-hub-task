package com.manju.kramphub.task.callback;

import java.util.List;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manju.kramphub.task.exceptions.KrampHubTaskException;
import com.manju.kramphub.task.model.TaskResponse;
import com.manju.kramphub.task.model.Type;
import com.manju.kramphub.task.services.BaseSearchApi;

/**
 * Class acts as a callback for apple search api which makes the request to apple itunes url to fetch the
 * albums based on limit and search value. Class runs in it's separate stack trace since it extends the callable interface.
 * 
 * @author manju
 * @version 1.0
 * @see java.util.concurrent.Callable
 *
 */
public class AppleSearchCallback implements Callable<Boolean> {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppleSearchCallback.class);
	
	private String searchValue;
	
	private int limit;
	
	private List<TaskResponse> resultList;
	
	private BaseSearchApi appleITunesSearchApi;
	
	public AppleSearchCallback(String searchValue, int limit, List<TaskResponse> resultList, BaseSearchApi appleITunesSearchApi) {
		this.searchValue = searchValue;
		this.limit = limit;
		this.resultList = resultList;
		this.appleITunesSearchApi = appleITunesSearchApi;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Boolean call() throws Exception {
		String responseJsonData = appleITunesSearchApi.searchApi(searchValue, limit);
		convertAlbumJsonToObject(responseJsonData, resultList);		
		return true;
	}
	
	/**
	 * Converts the response apple json data into custom object.
	 * 
	 * @param jsonData -- Json Data
	 * @param resultList -- Result list holds the converted object from json.
	 */
	private void convertAlbumJsonToObject(String jsonData, List<TaskResponse> resultList) {
		try {
			JSONObject object = new JSONObject(jsonData);
			JSONArray jsonArr = object.getJSONArray("results");
			TaskResponse responseObj = null;
			for(int i = 0; i < jsonArr.length(); i++) {
				responseObj = new TaskResponse();
				responseObj.setTitle(jsonArr.getJSONObject(i).getString("collectionName"));
				responseObj.setPersonName(jsonArr.getJSONObject(i).getString("artistName"));
				responseObj.setType(Type.ALBUM);
				resultList.add(responseObj);
			}
		} catch (JSONException e) {
			LOG.error("Error while converting apple response json to object.", e);
			throw new KrampHubTaskException("Error while converting apple response json to object.", e);
		}
	}
	
}

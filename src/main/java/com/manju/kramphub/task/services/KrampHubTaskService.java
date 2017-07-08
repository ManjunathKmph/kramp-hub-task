package com.manju.kramphub.task.services;

import java.util.List;

import com.manju.kramphub.task.model.ApiHealthStatus;
import com.manju.kramphub.task.model.TaskResponse;

/**
 * Search service for kramp hub task.
 * 
 * @author manju
 * @version 1.0
 *
 */
public interface KrampHubTaskService {
	
	/**
	 * Uses the executor service to invoke the callbacks for apple and google api's in parallel
	 * and combines the response from both the apis. Sorts the combined response based on the title.
	 * 
	 * @param searchValue -- Value to be searched
	 * @param limit -- Limit the response.
	 * @param apiHealthStatus -- Holds the health status of google and apple api.
	 * @return -- Returns the list which contains the task response objects fetched from apple and google api's.
	 */
	List<TaskResponse> search(String searchValue, int limit, ApiHealthStatus apiHealthStatus);
	
}

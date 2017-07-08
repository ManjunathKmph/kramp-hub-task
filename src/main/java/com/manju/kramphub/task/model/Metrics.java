package com.manju.kramphub.task.model;


/**
 * Holds the api metric information.
 * 
 * @author manju
 * @version 1.0
 *
 */
public class Metrics {
	
	private String apiStartTime;
	
	private String apiEndTime;
	
	private String apiCompleteTime;

	public String getApiStartTime() {
		return apiStartTime;
	}

	public void setApiStartTime(String apiStartTime) {
		this.apiStartTime = apiStartTime;
	}

	public String getApiEndTime() {
		return apiEndTime;
	}

	public void setApiEndTime(String apiEndTime) {
		this.apiEndTime = apiEndTime;
	}

	public String getApiCompleteTime() {
		return apiCompleteTime;
	}

	public void setApiCompleteTime(String apiCompleteTime) {
		this.apiCompleteTime = apiCompleteTime;
	}	

}

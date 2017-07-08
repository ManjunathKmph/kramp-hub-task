package com.manju.kramphub.task.model;

import java.util.List;

/**
 * Holds the combined response of metrics, health check and task response.
 * 
 * @author manju
 * @version 1.0
 *
 */
public class KrampHubTaskResponse {
	
	private int count;

	private List<TaskResponse> taskResponses;
	
	private Metrics apiMetrics;
	
	private ApiHealthStatus apiHealthStatus;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<TaskResponse> getTaskResponses() {
		return taskResponses;
	}

	public void setTaskResponses(List<TaskResponse> taskResponses) {
		this.taskResponses = taskResponses;
	}

	public Metrics getApiMetrics() {
		return apiMetrics;
	}

	public void setApiMetrics(Metrics apiMetrics) {
		this.apiMetrics = apiMetrics;
	}

	public ApiHealthStatus getApiHealthStatus() {
		return apiHealthStatus;
	}

	public void setApiHealthStatus(ApiHealthStatus apiHealthStatus) {
		this.apiHealthStatus = apiHealthStatus;
	}
	
}

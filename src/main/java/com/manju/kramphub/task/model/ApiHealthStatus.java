package com.manju.kramphub.task.model;

/**
 * Health status for API's.
 * 
 * PROCESSED -- Api is processed successfully.
 * PROCESSING_ERROR -- Api is not processed due to some exceptions/errors from the applie or google api's.
 * PARTIALLY_PROCESSED -- Api is partially processed. Got the response but while processing some exceptions occured like response may not contain authors/artist name or title etc.,
 * 
 * @author manju
 * @version 1.0
 *
 */
public class ApiHealthStatus {
	
	private ApiHealthStatusType googleApiStatus = ApiHealthStatusType.PROCESSED;
	
	private ApiHealthStatusType appleApiStatus = ApiHealthStatusType.PROCESSED;

	public ApiHealthStatusType getGoogleApiStatus() {
		return googleApiStatus;
	}

	public void setGoogleApiStatus(ApiHealthStatusType googleApiStatus) {
		this.googleApiStatus = googleApiStatus;
	}

	public ApiHealthStatusType getAppleApiStatus() {
		return appleApiStatus;
	}

	public void setAppleApiStatus(ApiHealthStatusType appleApiStatus) {
		this.appleApiStatus = appleApiStatus;
	}

	public static enum ApiHealthStatusType {
		
		PROCESSED, PROCESSING_ERROR, PARTIALLY_PROCESSED;
		
	}

}

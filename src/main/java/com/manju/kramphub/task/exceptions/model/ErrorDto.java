package com.manju.kramphub.task.exceptions.model;

/**
 * Custom error object which holds the error code and error message.
 * 
 * @author manju
 * @version 1.0
 *
 */
public class ErrorDto {
	
	private String errorCode;
	
	private String errorMessage;
	
	public ErrorDto() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

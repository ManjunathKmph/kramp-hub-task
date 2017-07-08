package com.manju.kramphub.task.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.manju.kramphub.task.exceptions.KrampHubTaskException;
import com.manju.kramphub.task.exceptions.model.ErrorDto;

/**
 * Custom Exception Handler class for this application. Creates user friendly error object with http status code
 * and the error message.
 * 
 * @author manju
 * @version 1.0
 *
 */
@ControllerAdvice
public class ExceptionHandler {
	
	/**
	 * Handles the Kramp hub task exception thrown from the controller classes
	 * and prepares the error object with http status code and the error message.
	 * 
	 * @param e -- KrampHubTaskException
	 * @return -- Returns the Response entity which holds the custom error object with http status code and the error message.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = KrampHubTaskException.class)
	public ResponseEntity<ErrorDto> handleEmployeeNotFoundException(KrampHubTaskException e) {
		ErrorDto errorDto  = new ErrorDto();
		errorDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorDto.setErrorMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
	}
	
	/**
	 * Handles the illegal argument exception thrown from the controller classes
	 * and prepares the error object with http status code and the error message.
	 * 
	 * @param e -- MethodArgumentNotValidException
	 * @return -- Returns the Response entity which holds the custom error object with http status code and the error message.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException e) {
		ErrorDto errorDto  = new ErrorDto();
		errorDto.setErrorCode(HttpStatus.BAD_REQUEST.toString());
		errorDto.setErrorMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
	}
	
}

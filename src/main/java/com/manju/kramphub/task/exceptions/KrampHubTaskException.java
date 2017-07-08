package com.manju.kramphub.task.exceptions;

/**
 * Kramp hub task exception thrown from the application while any error in processing the requests.
 * 
 * @author manju
 * @version 1.0
 * @see java.lang.RuntimeException
 *
 */
public class KrampHubTaskException extends RuntimeException {

	/**
	 * Generated Serial Version ID.
	 */
	private static final long serialVersionUID = -2182695050442347423L;
	
	public KrampHubTaskException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public KrampHubTaskException(String message) {
		super(message);
	}

}

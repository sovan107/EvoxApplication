package com.evox.web.exceptions;


import org.springframework.http.HttpStatus;

/**
 * A Generic Custom Runtime Exception for the Application
 * 
 */
public class APIException extends RuntimeException {
	private static final long serialVersionUID = 100L;

	private String message;
	private HttpStatus httpStatus;

	public APIException() {
		super();
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(String message, HttpStatus httpStatus) {
		this(message);
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

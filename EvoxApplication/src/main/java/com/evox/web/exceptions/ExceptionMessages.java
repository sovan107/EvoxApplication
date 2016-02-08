package com.evox.web.exceptions;

/**
 * Interface consist of constant error messages
 * 
 * @author nishant
 *
 */
public interface ExceptionMessages {

	String INVALID_ROLE_NAME = "Invalid Role Name";
	String INVALID_USERNAME = "Username is required!";
	String INVALID_PASSWORD = "Password is required!";
	String DUPLICATE_USER_NAME = "Duplicate Username";
	String USER_NOT_AVAILABLE = "Username is not available";

	String INVALID_LOGIN_JSON = "The login JSON is invalid. Expected format:"
			+ " \n{\n\t\"username\":\"username\",\n\t\"password\":\"password\"\n}";
}

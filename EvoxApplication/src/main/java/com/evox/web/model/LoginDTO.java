package com.evox.web.model;


//import static com.qwixerv.web.exceptions.ExceptionMessages.INVALID_PASSWORD;
//import static com.qwixerv.web.exceptions.ExceptionMessages.INVALID_USERNAME;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Login DTO object used to read login object.
 * @author Kaushik Sahoo
 */
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 2193176517499548985L;
	
	public LoginDTO() {
	}
	
	@NotBlank(message = "I un")
	private String username;
	
	@NotBlank(message = "I p")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
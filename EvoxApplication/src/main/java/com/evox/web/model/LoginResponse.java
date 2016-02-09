package com.evox.web.model;

public class LoginResponse {

    public String token;

	public LoginResponse(final String token) {
        this.token = token;
    }
    
	public String getToken() {
		return token;
	}

}

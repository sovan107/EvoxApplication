package com.sovan.dao;

public class LoginRequest {

	private String email;
    private String password;

    public LoginRequest(){}
    
    public LoginRequest(String email, String password){
    	this.email = email;
    	this.password = password;
    }
    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

}

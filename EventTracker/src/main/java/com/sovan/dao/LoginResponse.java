package com.sovan.dao;

public class LoginResponse {

	private String sessionId;
	private String status;

	public LoginResponse(String sessionId, String status){
		this.sessionId = sessionId;
		this.status = status;
	}
	public String getSessionId() {
		return sessionId;
	}

	public String getStatus() {
		return status;
	}
}

package com.evox.web.model;

import org.hibernate.validator.constraints.NotBlank;

public class User {

	public User(){
		
	}
	
	@NotBlank(message = "Invalid UserName")
	private String userName;
	@NotBlank(message = "Invalid Password")
	private String password;
	@NotBlank(message = "Invalid Company Name")
	private String company;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}

package com.evox.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name="user")
public class UserModel implements Serializable{

	private static final long serialVersionUID = 2193176517499548985L;
	public UserModel(){
		
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "Invalid UserName")
	@Column(name="user_name")
	private String userName;
	
	@NotBlank(message = "Invalid Password")
	@Column(name="password")
	private String password;

	@NotBlank(message = "Invalid Company Name")
	@Column(name="company")
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}

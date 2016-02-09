package com.evox.web.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.evox.web.model.User;
import com.evox.web.services.UserServices;

@RestController
public class UserController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(){
		System.out.println("Log out called");
	}
	
	@RequestMapping("/api/resource")
	public Map<String, Object> home() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		
		return model;
	}
	
	@RequestMapping(value = "/newUserRegistration", method = RequestMethod.POST)
	public @ResponseBody User saveNewUser(@RequestBody User user){
		
		System.out.println("User Registration Controller");
		System.out.println("User  : "  + user.getPassword());
		
		return user;
	}
}

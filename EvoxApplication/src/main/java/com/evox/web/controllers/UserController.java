package com.evox.web.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/user", method=RequestMethod.GET)
	public Principal user(Principal user) {
		
		System.out.println(user);
		return user;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public void logout(){
		System.out.println("Log out called");
	}
	
	@RequestMapping("/resource")
	public Map<String, Object> home() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}
}

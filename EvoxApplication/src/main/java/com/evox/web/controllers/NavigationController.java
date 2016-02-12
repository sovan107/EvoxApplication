package com.evox.web.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NavigationController {

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(){
	}
	
	@RequestMapping("/api/main")
	public Map<String, Object> home() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Main page : Hello World");
		
		return model;
	}
}

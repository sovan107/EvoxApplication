package com.evox.web.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evox.web.model.LoginResponse;
import com.evox.web.services.UserServices;

@RestController
public class UserController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(value = "/user", method=RequestMethod.POST)
	public LoginResponse user(Principal user) throws ServletException {
		
		if (user.getName() == null) {
            throw new ServletException("Invalid login");
        }
		
		return new LoginResponse(Jwts.builder().setSubject(user.getName())
	            .claim("roles", userService.findUserByUserName(user.getName())).setIssuedAt(new Date())
	            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
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

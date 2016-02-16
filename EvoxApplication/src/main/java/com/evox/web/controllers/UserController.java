/*
 * This is the user controller, which deals with all aspects related to User of evox application
 * */

package com.evox.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.evox.web.model.User;
import com.evox.web.services.impl.UserDetailsService;

@RestController
public class UserController {

	@Autowired
	UserDetailsService userServices;
	
	@RequestMapping(value = "/newUserRegistration", method = RequestMethod.POST)
	public @ResponseBody UserDetails saveNewUser(@RequestBody User user){
	
		userServices.saveUser(user);

		return user;
	}
}

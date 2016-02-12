/*
 * This is the user controller, which deals with all aspects related to User of evox application
 * */

package com.evox.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.evox.web.model.UserModel;
import com.evox.web.services.UserServices;

@RestController
public class UserController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(value = "/newUserRegistration", method = RequestMethod.POST)
	public @ResponseBody UserModel saveNewUser(@RequestBody UserModel user){
		
		UserModel retrivedUser = userService.findUserByUserName(user.getUserName());
		
		if(retrivedUser != null && 
				retrivedUser.getUserName().equals(user.getUserName())){
			
			return retrivedUser;
		} else {
			
			userService.saveUser(user);
		}
		
		return user;
	}
}

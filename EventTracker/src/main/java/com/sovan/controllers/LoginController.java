package com.sovan.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sovan.dao.LoginRequest;
import com.sovan.dao.LoginResponse;

@RestController
@EnableWebMvc
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST, headers="Accept=*/*",  produces="application/json")
	public @ResponseBody LoginResponse login(@RequestBody LoginRequest loginRequest){
		
		if(loginRequest.getEmail().equals("email") && 
				loginRequest.getPassword().equals("password")){
			
			LoginResponse response = new LoginResponse( UUID.randomUUID().toString(), "OK");

			System.out.println(response);
			return response;

		}
		
		LoginResponse response = new LoginResponse(null, "Invalid User Name and Password");
		 
		 return response;
	}
}

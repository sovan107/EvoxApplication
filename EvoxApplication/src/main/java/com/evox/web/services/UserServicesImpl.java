package com.evox.web.services;

import org.springframework.stereotype.Service;

import com.evox.web.model.User;

@Service
public class UserServicesImpl implements UserServices {

	@Override
	public User findUserByUserName(String userName) {
		
		if(userName.equals("mindfire")){
			
			User user = new User();
			
			user.setUserName("mindfire");
			user.setPassword("mfs");
			
			return user;
		} else {
			
			return null;
		}
	}

}

package com.evox.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.evox.web.dao.AbstractDao;
import com.evox.web.dao.UserDao;
import com.evox.web.model.UserModel;

@Repository("userRepository")
public class UserDaoImpl extends AbstractDao implements UserDao{

	@Override
	public UserModel findUserByUserName(String userName) {
		
		if(userName.equals("mindfire")){
			
			UserModel user = new UserModel();
			
			user.setUserName("mindfire");
			user.setPassword("mfs");
			user.setCompany("1234");
			
			return user;
		} else {
			
			return null;
		}
	}

	@Override
	public UserModel saveUser(UserModel user) {
		
		UserModel newUser = new UserModel();
		
		newUser.setUserName(user.getUserName());
		newUser.setCompany(user.getCompany());
		
		return newUser;
	}

	
}

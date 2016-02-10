package com.evox.web.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evox.web.dao.UserDao;
import com.evox.web.model.UserModel;
import com.evox.web.services.UserServices;

@Service("UserService")
@Transactional
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserModel findUserByUserName(String userName) {
		
		return userDao.findUserByUserName(userName);
	}

	@Override
	public UserModel saveUser(UserModel user) {
		
		return userDao.saveUser(user);
	}

}

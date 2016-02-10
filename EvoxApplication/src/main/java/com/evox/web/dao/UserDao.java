package com.evox.web.dao;

import com.evox.web.model.UserModel;

public interface UserDao {

	UserModel findUserByUserName(String userName);

	UserModel saveUser(UserModel user);
}

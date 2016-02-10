package com.evox.web.services;

import com.evox.web.model.UserModel;

public interface UserServices {

	UserModel findUserByUserName(String userName);

	UserModel saveUser(UserModel user);
}

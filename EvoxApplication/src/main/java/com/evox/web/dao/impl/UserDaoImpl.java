package com.evox.web.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.evox.web.dao.AbstractDao;
import com.evox.web.dao.UserDao;
import com.evox.web.model.UserModel;

@Repository("userRepository")
public class UserDaoImpl extends AbstractDao implements UserDao{

	@Override
	public UserModel findUserByUserName(String userName) {
		
		Query query = getSession()
				.createQuery("FROM user u WHERE u.userName LIKE :userName");
		
		query.setParameter("userName", userName);
		
		@SuppressWarnings("unchecked")
		List<UserModel> user = query.list();
		
		if(user.size() > 0){
			return user.get(0);
		} else {
			
			return null;
		}
	}

	@Override
	public UserModel saveUser(UserModel user) {
		
		persist(user);
		return user;
	}
}

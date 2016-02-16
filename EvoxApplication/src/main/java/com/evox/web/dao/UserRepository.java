package com.evox.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evox.web.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
package com.niit.dao;

import com.niit.model.User;

public interface UserDao {
	void registration(User user);
	 boolean isEmailValid(String email);
	 User login(User user);
	 void update(User user);
	User getUser(String email);
	}


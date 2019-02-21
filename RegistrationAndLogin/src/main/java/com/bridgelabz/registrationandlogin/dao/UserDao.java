package com.bridgelabz.registrationandlogin.dao;

import com.bridgelabz.registrationandlogin.model.LoginInfo;
import com.bridgelabz.registrationandlogin.model.User;

public interface UserDao {
	  int addUser(User user);
	  User checkUserIsPresent(LoginInfo login);
}

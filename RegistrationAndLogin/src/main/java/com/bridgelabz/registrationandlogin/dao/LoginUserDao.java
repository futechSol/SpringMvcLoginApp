package com.bridgelabz.registrationandlogin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bridgelabz.registrationandlogin.model.LoginInfo;
import com.bridgelabz.registrationandlogin.model.User;

public class LoginUserDao implements UserDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * adds a new user to the users list
	 * @param user new user to add
	 * @return number of rows affected if user got added, otherwise 0
	 */
	public int addUser(User user) {
		String sql = "insert into User values(?,?,?,?,?,?,?)";
		int rowsAffected;
		rowsAffected = jdbcTemplate.update(sql, new Object[] { user.getId(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(),
				user.getEmail(),user.getUserName(),user.getPassword()});
		return rowsAffected;
	}

	/**
	 * verifies whether the user with the specified login credentials exists
	 * @param login user login credentials
	 * @return returns user instance if exists otherwise null
	 */
	public User checkUserIsPresent(LoginInfo login) {
        String query = "select * from User where username = ? and password = ?";
        List<User> users = jdbcTemplate.query(query,  new Object[] { login.getUsername(), login.getPassword() }, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
	}
	
	/**
	 * checks the username already exists or not
	 * @param user User instance
	 * @return true if username is already exists else false
	 */
	public boolean isDuplicateUserName(User user) {
		String query = "Select * from User where username = ?";
		List<User> users = jdbcTemplate.query(query,  new Object[] { user.getUserName()}, new UserMapper());
        if(users.size() > 0 )
        	return true;
        else
        	return false;
	}
}

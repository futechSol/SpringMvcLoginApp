package com.bridgelabz.registrationandlogin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.bridgelabz.registrationandlogin.model.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	    User user = new User();
	    user.setFirstName(rs.getString("firstname"));
	    user.setLastName(rs.getString("lastname"));
	    user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phonenumber"));
        user.setUserName(rs.getString("username"));
	    user.setPassword(rs.getString("password"));
	    return user;
	}

}

package com.bridgelabz.registrationandlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.registrationandlogin.dao.LoginUserDao;
import com.bridgelabz.registrationandlogin.model.LoginInfo;
import com.bridgelabz.registrationandlogin.model.User;
import com.bridgelabz.registrationandlogin.util.Utility;

public class UserService {
	@Autowired
	LoginUserDao loginUserDao;
	
	/**
	 * registers a new user
	 * @param user new user instance
	 * @return a positive number if user got registered else zero
	 */
	public int register(User user) {
		return loginUserDao.addUser(user);
	}
	
	public User login(LoginInfo loginInfo) {
		return loginUserDao.checkUserIsPresent(loginInfo);
	}
	
	/**
	 * validates user details
	 * @param user User instance
	 * @return ModelAndView object if user details are not valid else null
	 */
	public ModelAndView validateUser(User user) {
		ModelAndView mav = new ModelAndView();
		if(user.getFirstName().contentEquals("") || user.getFirstName() == null || !Utility.validateStringForAlphanumericOflength(user.getFirstName(),20))
			return mav.addObject("message","invalid first name...!");
		else if(user.getLastName().contentEquals("") || user.getLastName() == null || !Utility.validateStringForAlphanumericOflength(user.getLastName(), 20))
			return mav.addObject("message","invalid lastst name...!");
		else if(user.getPhoneNumber().contentEquals("") || user.getPhoneNumber() == null || !Utility.validateMobileNumber(user.getPhoneNumber()))
			return mav.addObject("message","invalid mobile number...!");
		else if(user.getEmail().contentEquals("") || user.getEmail() == null || !Utility.validateEmail(user.getEmail()))
			return mav.addObject("message","invalid email...!");
		else if(user.getUserName().contentEquals("") || user.getUserName() == null || !Utility.validateStringForAlphanumericOflength(user.getUserName(), 20))
			return mav.addObject("message", "invalid username");
		else if(user.getPassword().contentEquals("") || user.getPassword() == null || !Utility.validateStringForAlphanumericOflength(user.getPassword(),20))
			return mav.addObject("message", "invalid password");
		else if(loginUserDao.isDuplicateUserName(user))
		    return mav.addObject("message", "username already exists...!");
		return null;
	}
	
	/**
	 * validates the login credentials
	 * @param loginInfo login credentials
	 * @return ModelAndView object if user details are not valid else null
	 */
	public ModelAndView validateLoginInfo(LoginInfo loginInfo) {
		ModelAndView mav = new ModelAndView();
		if(loginInfo.getUsername().contentEquals("") || loginInfo.getUsername() == null || !Utility.validateStringForAlphanumericOflength(loginInfo.getUsername(), 20))
			return mav.addObject("message", "invalid username");
		else if(loginInfo.getPassword().contentEquals("") || loginInfo.getPassword() == null || !Utility.validateStringForAlphanumericOflength(loginInfo.getPassword(),20))
			return mav.addObject("message", "invalid password");
		else return null;
	}
}

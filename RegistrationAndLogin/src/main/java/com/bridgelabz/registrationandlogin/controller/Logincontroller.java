package com.bridgelabz.registrationandlogin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.registrationandlogin.model.LoginInfo;
import com.bridgelabz.registrationandlogin.model.User;
import com.bridgelabz.registrationandlogin.service.UserService;

@Controller
public class Logincontroller {
	@Autowired
	UserService userService;


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new LoginInfo());
		return mav;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") LoginInfo loginInfo) {
		ModelAndView mav;
		mav = userService.validateLoginInfo(loginInfo);
		if(mav != null) {
			mav.setViewName("login");
			return mav;
		}
		User user = userService.login(loginInfo);
		if (user != null) {
			mav = new ModelAndView("success");
			mav.addObject("firstname", user.getFirstName());
		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong...!");
		}
		return mav;
	}
}

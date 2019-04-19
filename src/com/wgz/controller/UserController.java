package com.wgz.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wgz.pojo.User;
import com.wgz.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService;
	
	@RequestMapping("/showUser")
	public List<User> QueryUser(int id){
		log.info("showUser");
		List<User> username = userService.getUserById(id);
		System.out.println(username);
		return username;
	}
}
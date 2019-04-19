package com.wgz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wgz.dao.UserDao;
import com.wgz.pojo.User;

@Service("userService")
public class UserService {
	@Resource
	private UserDao userDao;
	
	public List<User> getUserById(int userId) {
		return this.userDao.getUser(userId);
	}
}

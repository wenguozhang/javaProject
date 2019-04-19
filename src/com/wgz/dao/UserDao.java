package com.wgz.dao;

import java.util.List;

import com.wgz.pojo.User;

public interface UserDao {
	public List<User> getUser(Integer id);
}

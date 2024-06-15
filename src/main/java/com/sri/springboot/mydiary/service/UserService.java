package com.sri.springboot.mydiary.service;

import java.util.List;

import com.sri.springboot.mydiary.entity.User;



public interface UserService {


	public User saveUser(User user);
	public User updateUser(User user);
	public void deleteUser(User user);
	public User insertUser(User user);
	public User findbyId(long id);
	public List<User> findAll();
	public User findbyUsername(String username);
}

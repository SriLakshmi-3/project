package com.sri.springboot.mydiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sri.springboot.mydiary.entity.User;
import com.sri.springboot.mydiary.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	 private UserRepository userRepository;
	@Override
	public User saveUser(User user) {
		 return userRepository.save(user);
		
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		 userRepository.delete(user);
	}

	@Override
	public User insertUser(User user) {
		return userRepository.save(user);
	}

	

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findbyId(long id) {
		
		return userRepository.findById(id).get();
	}

	@Override
	public User findbyUsername(String username) {
		return  userRepository.findbyUsername(username);
	}

}

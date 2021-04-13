package com.unosquare.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unosquare.entity.User;
import com.unosquare.repository.UserRepository;


@Component
public class UserDAO {
	
	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {
		User u = userRepository.save(user);
		
		u.getPassword();
		u.setPassword(null);
	    return u;
	}
	
	public User updateStatus(User user) {
		User u = userRepository.findById(user.getId()).get();
		u.setEnabled(user.getEnabled());
	    return userRepository.save(u);
	}
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	

	public User findById(String userId) {
		return userRepository.findById(userId).get();
	}
}

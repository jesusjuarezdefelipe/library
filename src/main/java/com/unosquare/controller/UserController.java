package com.unosquare.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.entity.User;
import com.unosquare.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	

	@PostMapping("/login")
	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws Exception {
		return userService.login(username, pwd);
	}
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) throws Exception {
		return userService.saveUser(user);
	}
	
	@PutMapping("/updateStatus")
	public User updateStatus(@RequestBody User user) throws Exception {
		return userService.updateStatus(user);
	}

	
}
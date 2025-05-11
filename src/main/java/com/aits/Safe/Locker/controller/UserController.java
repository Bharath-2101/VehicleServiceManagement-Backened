package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.entity.User;
import com.aits.Safe.Locker.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/admin/addUser")
	public String addUser(@RequestBody User user)
	{
		return userService.saveUser(user);
	}
	
	@GetMapping("/admin/allUsers")
	public List<User> allUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/admin/allServiceAdvisors")
	public List<User> allServiceAdvisors()
	{
		return userService.getAllServiceAdvisors();
	}

}

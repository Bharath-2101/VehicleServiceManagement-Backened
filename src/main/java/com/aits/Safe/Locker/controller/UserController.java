package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.DTO.UserDTO;
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
	public List<UserDTO> allUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/admin/allServiceAdvisors")
	public List<UserDTO> allServiceAdvisors()
	{
		return userService.getAllServiceAdvisors();
	}

}

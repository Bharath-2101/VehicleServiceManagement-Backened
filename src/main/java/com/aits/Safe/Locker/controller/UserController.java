package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.entity.User;
import com.aits.Safe.Locker.exception.NullValueException;
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
	
	@PutMapping("/admin/updateUser")
	public String updateUser(@RequestBody User user)
	{
		if(user.getId()==null)
		{
			throw new NullValueException("User Id is null");
		}
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/admin/deleteUser")
	public String deleteUser(@RequestParam Long id)
	{
		if(id==null)
		{
			throw new NullValueException("User Id is null");
		}
		return userService.deleteUser(id);
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

	@GetMapping("/check")
	public String checkingWhetherLoginOrNot()
	{
		return "hello";
	}
}

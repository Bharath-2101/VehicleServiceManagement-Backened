package com.aits.Safe.Locker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.User;
import com.aits.Safe.Locker.entity.User.Role;
import com.aits.Safe.Locker.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public String saveUser(User user)
	{
		List<User> existingUser=userRepository.findByEmailOrMobile(user.getEmail(),user.getMobile());
		if(!existingUser.isEmpty())
		{
			return "Users email or mobile is already exist";
		}
		user.setCreatedAt(LocalDateTime.now());
		
		userRepository.save(user);
		return "User added Successfully";
	}

	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public List<User> getAllServiceAdvisors()
	{
		return userRepository.findByRole(Role.SERVICE_ADVISOR);
	}
}

package com.aits.Safe.Locker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.DTO.UserDTO;
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

	public List<UserDTO> getAllUsers() {
	    List<User> users = userRepository.findAll();
	    List<UserDTO> modifiedUsers = new ArrayList<>();
	    for (User user : users) {
	        UserDTO modifiedUser = new UserDTO();
	        modifiedUser.setName(user.getName());
	        modifiedUser.setMobile(user.getMobile());
	        modifiedUser.setEmail(user.getEmail());
	        modifiedUser.setRole(user.getRole());
	        modifiedUser.setCreatedAt(user.getCreatedAt());
	        modifiedUsers.add(modifiedUser);
	    }
	    return modifiedUsers;
	}

	
	public List<UserDTO> getAllServiceAdvisors()
	{
		List<User> users=userRepository.findByRole(Role.SERVICE_ADVISOR);
		List<UserDTO> modifiedUsers = new ArrayList<>();
	    for (User user : users) {
	        UserDTO modifiedUser = new UserDTO();
	        modifiedUser.setName(user.getName());
	        modifiedUser.setMobile(user.getMobile());
	        modifiedUser.setEmail(user.getEmail());
	        modifiedUser.setRole(user.getRole());
	        modifiedUser.setCreatedAt(user.getCreatedAt());
	        modifiedUsers.add(modifiedUser);
	    }
	    return modifiedUsers;
	}
}

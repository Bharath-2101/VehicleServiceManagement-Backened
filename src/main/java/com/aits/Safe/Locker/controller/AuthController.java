package com.aits.Safe.Locker.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.entity.User;
import com.aits.Safe.Locker.entity.User.Role;
import com.aits.Safe.Locker.exception.InvalidCredentialsException;
import com.aits.Safe.Locker.exception.UserNotFoundException;
import com.aits.Safe.Locker.repo.UserRepository;
import com.aits.Safe.Locker.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
 
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;
 
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return "Email is  already in use";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        if (user.getRole() == null) {
            user.setRole(Role.SERVICE_ADVISOR);
        }

        userRepo.save(user);
        return "User registered!";
    }

 
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginUser) {
        User user = userRepo.findByEmail(loginUser.getEmail())
            .orElseThrow(() -> new UserNotFoundException("User not found"));
 
        if (encoder.matches(loginUser.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            return Map.of("token", token, "role", user.getRole().name(),"name",user.getName(),"id",String.valueOf(user.getId()),"email",user.getEmail());
        } else {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }
    
  
}
 
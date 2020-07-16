package com.api.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.security.model.User;
import com.api.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	public User findUserByName(String name) {
		
		return userRepository.findByName(name);
	}
	
	public User addUser(User user) {
		
		user.setPassword(bcryptEncoder.encode(user.getPassword())); //Encriptamos el password antes de guardarlo en la H2
		
		return userRepository.save(user);
	}
	
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}
	
}

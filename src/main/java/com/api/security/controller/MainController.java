package com.api.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.security.model.User;
import com.api.security.service.UserService;
import com.api.security.util.JwtUtil;

@RestController
public class MainController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//**************
	
	@GetMapping("/")
	public String securedEndpoint() {
		
		return "OK";
	}
	
	
	@PostMapping("/sign-up")
	public User signUp(@RequestBody User user) {
		
		return userService.addUser(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
			
		}catch(Exception ex) {
		
			return "Invalid Username/Password";
		
		}
		
		//Devolvemos el token para el usuario logeado
		return jwtUtil.generateToken(user.getName());
	}
	
	@GetMapping("/user")
	public List<User> findAllUsers(){
		
		return userService.findAllUsers();
	}
}

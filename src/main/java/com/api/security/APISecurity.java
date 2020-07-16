package com.api.security;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.security.model.Role;
import com.api.security.model.User;
import com.api.security.repository.UserRepository;

@SpringBootApplication
public class APISecurity {
	
	@Autowired
	private UserRepository UserRepository;
/*	
	@PostConstruct
	public void initUsers() {
		List<User> users = Stream.of(
				new User(1, "Tony Crespo", "password", "tonycrespo@outlook.com", true, null),
				new User(2, "Jhno Dow", "password", "jhondoe@facebook.com", true, null),
				new User(3, "Cris Alvin", "password", "crisalvin@yahoo.com", true, null),
				new User(4, "Tomas Cook", "password", "tomascooko@gmail.com", true, null)
				).collect(Collectors.toList());
		
		UserRepository.saveAll(users);
	}
	
	
	@PostConstruct
	public void initRoles() {
		List<Role> roles = Stream.of(
				new Role(1, "USER", null),
				new Role(2, "ADMIN", null)
			).collect(Collectors.toList());
	}
*/

	public static void main(String[] args) {
		SpringApplication.run(APISecurity.class, args);
	}

}

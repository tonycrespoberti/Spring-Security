package com.api.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByName(String name);
	
}

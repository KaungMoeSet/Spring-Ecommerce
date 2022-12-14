package com.ecommerce.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Role;
import com.ecommerce.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	List<User> findByRole(Role role);
}

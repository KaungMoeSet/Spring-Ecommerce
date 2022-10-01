package com.ecommerce.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.ecommerce.dto.JwtResponse;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.model.Address;
import com.ecommerce.model.User;

public interface UserService {

	List<User> getAllUsers();	
	public JwtResponse login(String username, String password);
	public String register(RegisterRequest registerRequest);
	public User addAddress(Address address, Authentication auth);
}

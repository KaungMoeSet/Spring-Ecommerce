package com.ecommerce.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.ecommerce.dto.JwtResponse;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.RegisterResponse;
import com.ecommerce.model.Address;
import com.ecommerce.model.User;

public interface UserService {

	List<User> getUsersByPage(int pageNo, int size);
	List<User> getAllUsers();	
	JwtResponse login(String username, String password);
	RegisterResponse register(RegisterRequest registerRequest);
	User addAddress(Address address, Authentication auth);
}

package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.User;

public interface UserService {

	List<User> getAllUsers();	
	public String login(String username, String password);
	public String register(User user);
}

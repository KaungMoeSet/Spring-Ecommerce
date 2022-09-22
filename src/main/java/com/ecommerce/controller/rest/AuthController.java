package com.ecommerce.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.Token;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
@CrossOrigin
@RequestMapping
public class AuthController {

	@Autowired
	UserService userService;
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public Token login(@Valid @RequestBody User user) {
		Token token = new Token();
		String tok = userService.login(user.getUsername(), user.getPassword());
		token.setToken(tok);
		return token;
	}
	
	@PostMapping("/register")
	public String register(@Valid @RequestBody User user) {
		return userService.register(user);
	}
}

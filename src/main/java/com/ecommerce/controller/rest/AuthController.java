package com.ecommerce.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.JwtResponse;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.RegisterResponse;
import com.ecommerce.dto.Token;
import com.ecommerce.model.Address;
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
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.login(loginRequest.getUsername(), loginRequest.getPassword()));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest ) throws JSONException {
		RegisterResponse response = userService.register(registerRequest);
		if(response.isUserExisted()) {
			return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/addAddress")
	public ResponseEntity<User> addAddress(@RequestBody Address address, Authentication auth){
		User user = userService.addAddress(address, auth);
		return ResponseEntity.ok(user);
	}
}

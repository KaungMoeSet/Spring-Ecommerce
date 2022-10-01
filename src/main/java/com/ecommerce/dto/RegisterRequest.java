package com.ecommerce.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.ecommerce.model.Gender;

import lombok.Data;

@Data
public class RegisterRequest {

	@NotEmpty
	@Size(max = 20)
	private String username;

	private Gender gender;
	
	@Email
	@Size(max = 50)
	private String email;
	
	@NotEmpty
	@Size(max = 20,min = 8, message = "Minimum password length: 8 characters")
	private String password;

	@DBRef
	private List<String> roles;
}

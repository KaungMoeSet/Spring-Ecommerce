package com.ecommerce.dto;

import java.util.List;

import com.ecommerce.model.Gender;
import com.ecommerce.model.Role;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	
	private String username;
	
	private Gender gender;
	
	private String email;
	
	private List<Role> roles;
}

package com.ecommerce.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User extends BaseDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	private Set<Role> role;
	
	@DBRef
	private Address address;

	private Boolean locked = true;

	private Boolean enabled = true;

	
	public User(String username, Gender gender, String email, String password) {
		super();
		this.username = username;
		this.gender = gender;
		this.email = email;
		this.password = password;
	}
}

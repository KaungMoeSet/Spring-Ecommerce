package com.ecommerce.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@NotEmpty
	private String username;

	private Gender gender;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 8, message = "Minimum password length: 8 characters")
	private String password;

	private List<Role> roles;

	private Boolean locked = true;

	private Boolean enabled = true;

	
	public User(String username, Gender gender, String email, String password, List<Role> roles) {
		super();
		this.username = username;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.toString());
		return Collections.singletonList(authority);
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}

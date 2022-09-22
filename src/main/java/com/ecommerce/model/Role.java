package com.ecommerce.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String role;
	
	@Override
	public String getAuthority() {
		return this.role;
	}

}

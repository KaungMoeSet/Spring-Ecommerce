package com.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Document(collection="role")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private ERole name;

	@Override
	public String getAuthority() {
		return name.toString();
	}
	
}

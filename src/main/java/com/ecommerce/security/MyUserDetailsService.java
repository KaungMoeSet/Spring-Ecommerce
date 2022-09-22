package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRespository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRespository.findByUsername(username);
		System.out.println("Find by username " + username + " Got user " + user);
		
		if( user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		return org.springframework.security.core.userdetails.User//
				.withUsername(username)//
				.password(user.getPassword())//
				.authorities(user.getRoles())//
				.accountExpired(user.isAccountNonExpired())//
				.accountLocked(!user.isAccountNonLocked())//
				.credentialsExpired(user.isCredentialsNonExpired())//
				.disabled(!user.isEnabled())//
				.build();
	}

}

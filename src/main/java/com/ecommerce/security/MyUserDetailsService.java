package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.UserRepository;
import com.ecommerce.dto.MyUserDetails;
import com.ecommerce.model.User;

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
		
		return MyUserDetails.build(user);
	}

}

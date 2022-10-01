package com.ecommerce.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.JwtResponse;
import com.ecommerce.dto.MyUserDetails;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.model.Address;
import com.ecommerce.model.ERole;
import com.ecommerce.model.Role;
import com.ecommerce.model.User;
import com.ecommerce.repository.AddressRepository;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.JwtTokenProvider;
import com.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public JwtResponse login(String username, String password) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
			List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			String jwtToken = jwtTokenProvider.createToken(username,
					userRepository.findByUsername(username).getRoles());

			return new JwtResponse(jwtToken, user.getId(), user.getUsername(), user.getPassword(), roles);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid username/password supplied");
		}
	}

	@Override
	public String register(RegisterRequest registerRequest) {
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			return "Error: Username is already taken!";
		}

		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			return "Error: Email is already in use!";
		}

		User user = new User(registerRequest.getUsername(), registerRequest.getGender(), registerRequest.getEmail(),
				passwordEncoder.encode(registerRequest.getPassword()));

		List<String> regRole = registerRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (regRole == null) {
			Role userRole = roleRepository.findByName(ERole.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			regRole.forEach(checkRole -> {
				if (checkRole.equals("admin")) {
					Role adminRole = roleRepository.findByName(ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
				} else {
					Role userRole = roleRepository.findByName(ERole.USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}

			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return "User Registered Successfully";
	}

	@Override
	public User addAddress(Address address, Authentication auth) {
		User user = userRepository.findByUsername(auth.getName());
		Address userAddress = user.getAddress();
		if (userAddress != null) {
			userAddress.setAddress(address.getAddress());
			userAddress.setCity(address.getCity());
			userAddress.setState(address.getState());
			userAddress.setCountry(address.getCountry());
			userAddress.setZipCode(address.getZipCode());
			userAddress.setPhoneNumber(address.getPhoneNumber());
			addressRepository.save(userAddress);
		} else {
			user.setAddress(address);
			addressRepository.save(address);
		}
		System.out.println(auth.getAuthorities());
		return user;
	}
}

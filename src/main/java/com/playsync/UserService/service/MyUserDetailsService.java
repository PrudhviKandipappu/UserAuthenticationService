package com.playsync.UserService.service;

import org.springframework.stereotype.Service;

import com.playsync.UserService.model.UserPrincipal;
import com.playsync.UserService.model.Users;
import com.playsync.UserService.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
	    
		return new UserPrincipal(user);
	}

}

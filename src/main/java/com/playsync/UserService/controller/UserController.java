package com.playsync.UserService.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.playsync.UserService.dto.Role;
import com.playsync.UserService.model.UserPrincipal;
import com.playsync.UserService.model.Users;
import com.playsync.UserService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		return userService.register(user);
	}
	
	@GetMapping("/me")
	public ResponseEntity<Users> authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserPrincipal currentUser = (UserPrincipal) authentication.getPrincipal();
		
		return ResponseEntity.ok(currentUser.getUser());
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Users>> allUsers() {
		List<Users> users = userService.allUsers();
		
		return ResponseEntity.ok(users);
	}
	
//	@GetMapping("/admin")
//	public ResponseEntity<List<Users>> allPlayers() {
//		List<Users> users = userService.allUsers().stream()
//			    .filter(user -> user.getRoles().contains(Role.ADMIN))
//			    .collect(Collectors.toList());
//		
//		//users.forEach(user -> System.out.println(user.getRoles()));
//		
//		return ResponseEntity.ok(users);
//	}
	
//	@GetMapping("/stadium")
//	public ResponseEntity<List<Users>> allStadiums() {
//		List<Users> users = userService.allUsers().stream()
//			    .filter(user -> user.getRoles().contains(Role.STADIUM_MANAGER))
//			    .collect(Collectors.toList());
//		
//		
//		return ResponseEntity.ok(users);
//	}
	
}

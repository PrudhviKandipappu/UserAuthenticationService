package com.playsync.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.playsync.UserService.dto.LoginUserDto;
import com.playsync.UserService.service.UserService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginUserDto user) {
		return userService.verify(user);
	}
}

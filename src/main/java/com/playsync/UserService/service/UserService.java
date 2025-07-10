package com.playsync.UserService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.playsync.UserService.dto.LoginUserDto;
import com.playsync.UserService.dto.Role;
import com.playsync.UserService.model.Users;
import com.playsync.UserService.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private JWTService jwtService;
    
	@Autowired
	private GeocodingService geocodingService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    
    public Optional<Users> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	public List<Users> allUsers() {
		return userRepository.findAll();
	}
	
	public Users register(Users newUser) {
		double[] coordinates = geocodingService.getCoordinatesFromAddress(newUser.getAddress());
		
		if (coordinates == null || coordinates.length < 2) {
		    throw new RuntimeException("Invalid address! Unable to fetch coordinates.");
		}
		
		newUser.setLocation(new GeoJsonPoint(coordinates[1], coordinates[0]));
		newUser.setPassword(encoder.encode(newUser.getPassword()));
		
		if (newUser.getRoles().contains(Role.ADMIN) || newUser.getRoles().contains(Role.STADIUM_MANAGER)) newUser.getRoles().add(Role.PLAYER);

		
		Users savedUser = userRepository.save(newUser);
		
		return savedUser;
	}

	public String verify(LoginUserDto user) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		
		if (authentication.isAuthenticated()) return jwtService.generateToken(user.getEmail());
		return "Failed";
	}
}

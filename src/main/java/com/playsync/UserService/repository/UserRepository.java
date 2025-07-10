package com.playsync.UserService.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.playsync.UserService.model.Users;

public interface UserRepository extends MongoRepository<Users, String>{
	
	Optional<Users> findByEmail(String email);
	
}

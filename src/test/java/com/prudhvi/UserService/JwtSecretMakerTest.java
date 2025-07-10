package com.prudhvi.UserService;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.security.Keys;

public class JwtSecretMakerTest {
	
	@Test
	public void generateSecretKey() {
	    // Generate a random secret key
	    SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

	    // Encode the key in Base64 format to use as a string
	    String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());

	    // Print the Base64-encoded key
	    System.out.println("Key = " + encodedKey);
	}

}

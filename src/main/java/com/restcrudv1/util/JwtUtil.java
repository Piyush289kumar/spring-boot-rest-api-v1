package com.restcrudv1.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.restcrudv1.entity.Role;
import com.restcrudv1.entity.User;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String JWT_SECRET = "myjwtsecretkey1234myjwtsecretkey1234";
	private final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
	
	public String generateToken(User user) {
		
		return Jwts.builder()
				.subject(user.getEmail())
				.claim("roles", user.getRoles()
						.stream()
						.map(Role::getName)
						.toList())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
				.signWith(key)
				.compact();
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public String extractEmail(String token) {
		
		return Jwts.parser()
				.verifyWith((SecretKey) key)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

}

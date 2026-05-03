package com.restcrudv1.util;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String JWT_SECRET = "myjwtsecretkey1234myjwtsecretkey1234";
	private final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
	
	public String generateToken(String email) {
		
		return Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
				.signWith(key)
				.compact();
	}
	
	public String extractEmail(String token) {
		
		return Jwts.parser()
				.verifyWith((javax.crypto.SecretKey) key)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

}

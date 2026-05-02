package com.restcrudv1.util;

import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	
	private final String JWT_SECRET = "myjwtsecretkey";
	private final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

}

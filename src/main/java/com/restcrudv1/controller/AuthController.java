package com.restcrudv1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restcrudv1.dto.request.LoginRequest;
import com.restcrudv1.dto.request.RegisterRequest;
import com.restcrudv1.dto.response.AuthResponse;
import com.restcrudv1.service.implementations.AuthService;
import com.restcrudv1.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService auth_service;
	private final JwtUtil jwt;
	
	public AuthController(AuthService auth_service, JwtUtil jwt) {
		this.auth_service = auth_service;
		this.jwt = jwt;				
	}
	
	@PostMapping("/register")	
	public String register(@RequestBody RegisterRequest req) {
		auth_service.register(req.name, req.email, req.password);
		return "User register successfully.";
	}
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest req) {
		var user = auth_service.login(req.email, req.password);
		String token = jwt.generateToken(user.getUser_email());
		return new AuthResponse(token);
	}

}

package com.restcrudv1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restcrudv1.dto.request.LoginRequest;
import com.restcrudv1.dto.request.RegisterRequest;
import com.restcrudv1.dto.response.AuthResponse;
import com.restcrudv1.service.implementations.AuthService;
import com.restcrudv1.util.JwtUtil;
import com.restcrudv1.util.ResponseUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService auth_service;
	private final JwtUtil jwt;
	
	public AuthController(AuthService auth_service, JwtUtil jwt) {
		this.auth_service = auth_service;
		this.jwt = jwt;				
	}
	
	@PostMapping("/register")	
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
		auth_service.register(req.name, req.email, req.password);
		return ResponseUtil.success(null, "User registered successfully.");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
		var user = auth_service.login(req.email, req.password);
		String token = jwt.generateToken(user.getUser_email());
		return ResponseUtil.success(new AuthResponse(token), "Login successful.");
	}

}

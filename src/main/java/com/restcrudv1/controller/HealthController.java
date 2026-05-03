package com.restcrudv1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restcrudv1.dto.request.ApiResponse;
import com.restcrudv1.util.ResponseUtil;

@RestController
public class HealthController {

	@GetMapping("/")
	public ResponseEntity<ApiResponse<Object>> home() {
		return ResponseUtil.success(null, "Server is runninng...");
	}
	
	@GetMapping("/health")
	public ResponseEntity<ApiResponse<Object>> health() {
		return ResponseUtil.success(null, "ok");
	}
}

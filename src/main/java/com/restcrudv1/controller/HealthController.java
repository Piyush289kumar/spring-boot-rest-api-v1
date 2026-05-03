package com.restcrudv1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@GetMapping("/")
	public String home() {
		return "Server is running..";
	}
	
	@GetMapping("/health")
	public String health() {
		return "Ok";
	}
}

package com.restcrudv1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	
	@NotBlank(message = "Name is required.")
	public String name;
	
	@Email(message = "Invalid Email")
	@NotBlank(message = "Email is required.")
	public String email;
	
	@Size(min = 6, message = "Password must be at least 6 characters.")
	public String password;
}

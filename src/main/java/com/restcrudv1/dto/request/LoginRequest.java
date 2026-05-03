package com.restcrudv1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotBlank(message = "Email is required.")
	@Email(message = "Invalid Email")
	public String email;

	@NotBlank(message = "Password is required.")
	public String password;
}

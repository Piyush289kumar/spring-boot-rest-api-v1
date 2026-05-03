package com.restcrudv1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotBlank(message = "Email is required.")
	@Email(message = "Invalid email format")
	@Pattern(
	    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
	    message = "Email must contain valid domain (e.g. example@gmail.com)"
	)
	public String email;

	@NotBlank(message = "Password is required.")
	public String password;
}

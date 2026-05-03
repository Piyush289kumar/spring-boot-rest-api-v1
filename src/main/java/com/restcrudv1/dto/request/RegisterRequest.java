package com.restcrudv1.dto.request;

import jakarta.validation.constraints.*;

public class RegisterRequest {
	
	@NotBlank(message = "Name is required.")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters and spaces.")
	public String name;
	
	@NotBlank(message = "Email is required.")
	@Email(message = "Invalid email format")
	@Pattern(
	    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
	    message = "Email must contain valid domain (e.g. example@gmail.com)"
	)
	public String email;

	@NotBlank(message = "Password is required.")
	@Size(min = 8, message = "Password must be at least 8 characters.")
	@Pattern(
		  regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
		  message = "Password must contain uppercase, lowercase, number, and special character"
	)
	public String password;
}

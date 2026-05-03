package com.restcrudv1.dto.request;

import jakarta.validation.constraints.*;

public class RegisterRequest {
	
	@NotBlank(message = "Name is required.")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters and spaces.")
	public String name;
	
	@NotBlank(message = "Email is required.")
	@Email(message = "Invalid email formate")
	public String email;

	@NotBlank(message = "Password is required.")
	@Size(min = 8, message = "Password must be at least 8 characters.")
	@Pattern(
		  regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
		  message = "Password must contain uppercase, lowercase, number, and special character"
	)
	public String password;
}

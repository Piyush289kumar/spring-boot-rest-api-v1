package com.restcrudv1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUserProfileRequest {
		
	@NotBlank(message = "Name is required.")
	@Size(min = 3, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters allowed.")
	public String name;
	
	 @NotBlank(message = "Email is required")
	 @Email(message = "Invalid email")
	 @Pattern(
	        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
	        message = "Email must contain valid domain"
	 )
	 public String email;
	 	 
	 public boolean isActive;
}

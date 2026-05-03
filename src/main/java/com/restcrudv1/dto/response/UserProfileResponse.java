package com.restcrudv1.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public class UserProfileResponse {
	
	public Long id;
	public String name;
	public String email;
	public boolean isActive;
	public LocalDateTime createdAt;
	public Set<String> roles;
	
	public UserProfileResponse(Long id, String name, String email, boolean isActive, LocalDateTime createdAt,
			Set<String> roles) {
			
		this.id = id;
		this.name = name;
		this.email = email;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.roles = roles;
	}
	
	
	
}

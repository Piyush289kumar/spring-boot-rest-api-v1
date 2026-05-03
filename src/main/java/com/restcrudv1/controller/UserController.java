package com.restcrudv1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restcrudv1.dto.request.UpdateUserProfileRequest;
import com.restcrudv1.service.implementations.UserService;
import com.restcrudv1.util.ResponseUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	
	// Get Profile
	@GetMapping("/me")
	@PreAuthorize("hasAnyRole('USER','OFFICER','CUSTOMER')")
	public ResponseEntity<?> getProfile(Authentication auth){
		String email = auth.getName();
		
		return ResponseUtil.success(service.getProfileByEmail(email),
				"My profile fetched");
	}
	
	
	// Update my profile
	
    @PutMapping("/me")
    @PreAuthorize("hasAnyRole('USER','OFFICER','CUSTOMER')")
    public ResponseEntity<?> updateMyProfile(
            Authentication auth,
            @Valid @RequestBody UpdateUserProfileRequest req) {

        String email = auth.getName();

        return ResponseUtil.success(
                service.updateProfile(email, req.name, req.email, req.isActive),
                "Profile updated successfully"
        );
    }

	
}





























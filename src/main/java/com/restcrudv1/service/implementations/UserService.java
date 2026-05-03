package com.restcrudv1.service.implementations;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restcrudv1.dto.response.UserProfileResponse;
import com.restcrudv1.entity.User;
import com.restcrudv1.exception.ApiException;
import com.restcrudv1.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
		// Get Profile By Email - JWT based
		public UserProfileResponse getProfileByEmail(String email) {
		
				User user = userRepo.findByEmail(email)
						.orElseThrow(() -> new ApiException("Usernot found."));
				
				Set<String> roles = user.getRoles()
										.stream()
										.map(r -> r.getName())
										.collect(Collectors.toSet());
				
				return new UserProfileResponse(
						user.getId(),
						user.getName(),
						user.getEmail(),
						user.isActive(),
						user.getCreatedAt(),
						roles);		

	}
		
		// ✅ UPDATE PROFILE (JWT based)
	    public UserProfileResponse updateProfile(String email, String name, String newEmail, boolean isActive) {

	        User user = userRepo.findByEmail(email)
	                .orElseThrow(() -> new ApiException("User not found"));

	        // email uniqueness check
	        userRepo.findByEmail(newEmail).ifPresent(existing -> {
	            if (!existing.getId().equals(user.getId())) {
	                throw new ApiException("Email already in use");
	            }
	        });

	        user.setUser_name(name);
	        user.setUser_email(newEmail);
	        user.setActive(isActive);

	        userRepo.save(user);

	        return getProfileByEmail(newEmail);
	    }
		
	
	
}

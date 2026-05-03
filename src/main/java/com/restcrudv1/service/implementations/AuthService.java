package com.restcrudv1.service.implementations;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restcrudv1.entity.Role;
import com.restcrudv1.entity.User;
import com.restcrudv1.exception.ApiException;
import com.restcrudv1.repository.RoleRepository;
import com.restcrudv1.repository.UserRepository;

@Service
public class AuthService {
	
	private final UserRepository repo;
	private final PasswordEncoder encoder;
	private final RoleRepository roleRepo;
	
	public AuthService(UserRepository repo, PasswordEncoder encoder, RoleRepository roleRepo) {
		this.repo = repo;
		this.encoder = encoder;
		this.roleRepo = roleRepo;
	}
	
	public User register(String name, String email, String password, String roleName) {
		
		if(repo.findByEmail(email).isPresent()) {
			throw new ApiException("Email already registered.");
		}
		
		User user = new User();
		user.setUser_name(name);
		user.setUser_email(email);
		user.setPassword(encoder.encode(password));
		
		Role role = roleRepo.findByName(roleName.toUpperCase())
		        .orElseThrow(() -> new ApiException("Role not found: " + roleName));

		user.getRoles().add(role);
		
		return repo.save(user);		
	}
	
	public User login(String email, String password) {
		User user = repo.findByEmail(email)
				.orElseThrow(() -> new ApiException("User not found") );
		
		if(!encoder.matches(password, user.getPassword())) {
			throw new ApiException("Invalid password");
		}
		
		return user;
	}
}
package com.restcrudv1.service.implementations;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restcrudv1.entity.User;
import com.restcrudv1.repository.UserRepository;

@Service
public class AuthService {
	
	private final UserRepository repo;
	private final PasswordEncoder encoder;
	
	public AuthService(UserRepository repo, PasswordEncoder encoder) {
		this.repo = repo;
		this.encoder = encoder;
	}
	
	public User register(String name, String email, String password) {
		User user = new User();
		user.setUser_name(name);
		user.setUser_email(email);
		user.setPassword(encoder.encode(password));
		return repo.save(user);		
	}
	
	public User login(String email, String password) {
		User user = repo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found") );
		
		if(!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
		
		return user;
	}
}
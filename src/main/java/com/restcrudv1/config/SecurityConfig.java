package com.restcrudv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.restcrudv1.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {
	
	private final JwtAuthFilter jwtFilter;
	
	public SecurityConfig(JwtAuthFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					
					// public endpoints					
					.requestMatchers("/health", "/api/v1/auth/**").permitAll()
					
					// everything under /api/** required auth
					.requestMatchers("/api/**").authenticated()
					
					// anything else
					.anyRequest().authenticated()					
					)
					.httpBasic(b -> b.disable())
					.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

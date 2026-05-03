package com.restcrudv1.security;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.restcrudv1.util.JwtUtil;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	
	public JwtAuthFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain filterChain)
				   throws ServletException, IOException
	{
		
		String authHeader = req.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			
			try {
				
				Claims claims = jwtUtil.extractAllClaims(token);
							
				String email = claims.getSubject();
				
				List<String> roles = claims.get("roles", List.class);
				
				if(roles == null) {
					roles = List.of();
				}
				
				List<SimpleGrantedAuthority> authorities = roles.stream()
	                    .map(SimpleGrantedAuthority::new)
	                    .collect(Collectors.toList());							
				
				var auth = new UsernamePasswordAuthenticationToken(email, null, authorities);
				
				SecurityContextHolder.getContext().setAuthentication(auth);				
				
			}catch(Exception e) {
				System.out.println("JWT Error: " + e.getMessage());
			}
		}
		
		filterChain.doFilter(req, res);
		
	}
	
}














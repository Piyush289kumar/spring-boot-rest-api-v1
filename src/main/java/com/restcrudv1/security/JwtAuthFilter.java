package com.restcrudv1.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.restcrudv1.util.JwtUtil;

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
				
				String email = jwtUtil.extractEmail(token);
				
				var auth = new UsernamePasswordAuthenticationToken(email, null, null);
				
				SecurityContextHolder.getContext().setAuthentication(auth);				
				
			}catch(Exception e) {
				
			}
		}
		
		filterChain.doFilter(req, res);
		
	}
	
}














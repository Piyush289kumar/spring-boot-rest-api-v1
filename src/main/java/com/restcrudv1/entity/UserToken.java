package com.restcrudv1.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tokens", indexes = {
		@Index(name = "idx_user_id", columnList = "user_id"),
		@Index(name = "idx_access_token", columnList = "access_token"),
		@Index(name = "idx_refresh_token", columnList = "refresh_token")
})

public class UserToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "access_token", length = 512)
	private String accessToken;
	
	@Column(name = "refresh_token", length = 512)
	private String refreshToken;
	
	@Column(name = "access_expiry")
	private LocalDateTime accessExpiry;
	
	@Column(name = "refresh_expiry")
	private LocalDateTime refreshExpiry;
	
	@Column(name = "is_revoked")
	private boolean isRevoked = false;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	
}

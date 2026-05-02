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
@Table(name = "user_logs", indexes = {
		@Index(name = "idx_user_log_user_id", columnList = "user_id")
})
public class UserLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long userId;
	
	@Column
	private String action;
	
	@Column
	private String ipAddress;
	
	@Column
	private String userAgent;
	
	@Column
	private String device;
	
	@Column
	private String location;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
}

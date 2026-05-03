package com.restcrudv1.util;

import org.springframework.http.ResponseEntity;

import com.restcrudv1.dto.request.ApiResponse;

public class ResponseUtil {

	public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message){
		return ResponseEntity.ok(
				new ApiResponse<T>(true, 200, message, data)
				);
	}
	
	
	public static <T> ResponseEntity<ApiResponse<T>> create(T data, String message){
		return ResponseEntity.status(201)
				.body( new ApiResponse<T>(true, 201, message, data));
	}
	
	public static <T> ResponseEntity<ApiResponse<T>> error(int status, String message){
		return ResponseEntity.status(status)
				.body( new ApiResponse<T>(false, status, message, null));
	}
}

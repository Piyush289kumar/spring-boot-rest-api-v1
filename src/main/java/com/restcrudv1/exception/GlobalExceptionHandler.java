package com.restcrudv1.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.restcrudv1.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Validation Errors (@Valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(err ->
				errors.put(err.getField(), err.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponse<>(false, 400, "Validation failed.", errors));
	}
	
	// Custome Runtime Errors
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse<Object>> handlerRuntime(RuntimeException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponse<>(false, 400, ex.getMessage(), null));
	}
	
	// Generic Errors (fallback)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleAll(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse<>(false, 500, "Something went wrong.", null));
	}
	
}

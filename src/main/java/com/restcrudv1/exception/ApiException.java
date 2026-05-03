package com.restcrudv1.exception;

public class ApiException extends RuntimeException{
	public ApiException(String message) {
		super(message);
	}
}

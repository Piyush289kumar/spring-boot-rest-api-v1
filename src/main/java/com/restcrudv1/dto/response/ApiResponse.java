package com.restcrudv1.dto.response;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ApiResponse<T> {

		private boolean success;
		private int status_code;
		private String message;
		private T data;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY HH:mm:ss") 
		private LocalDateTime timestamp;
		
		public ApiResponse(boolean success, int status_code, String message, T data) {
			super();
			this.success = success;
			this.status_code = status_code;
			this.message = message;
			this.data = data;			
			this.timestamp = LocalDateTime.now();
		}

		public boolean isSuccess() {
			return success;
		}
		
		public int getStatus_code() {
			return status_code;
		}
		
		public String getMessage() {
			return message;
		}
	
		public T getData() {
			return data;
		}		

		public LocalDateTime getTimestamp() {
			return timestamp;
		}		
		
}
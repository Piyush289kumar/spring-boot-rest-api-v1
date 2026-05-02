package com.restcrudv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestCrudV1Application {

	public static void main(String[] args) {
		SpringApplication.run(RestCrudV1Application.class, args);
		System.out.println("Server is UP...");
	}

}

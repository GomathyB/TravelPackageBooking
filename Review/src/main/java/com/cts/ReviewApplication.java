package com.cts; // Defines the package for the Review microservice

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Marks this class as a Spring Boot application
@EnableFeignClients // Enables Feign Clients for inter-service communication
public class ReviewApplication {

	public static void main(String[] args) {
		// Starts the Review microservice
		SpringApplication.run(ReviewApplication.class, args);
	}
}

package com.cts; // Defines the package for the Payment microservice

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Marks this class as a Spring Boot application
@EnableFeignClients // Enables Feign Clients for inter-service communication
public class PaymentApplication {

	public static void main(String[] args) {
		// Starts the Payment microservice
		SpringApplication.run(PaymentApplication.class, args);
	}
}

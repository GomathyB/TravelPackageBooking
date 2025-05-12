package com.cts; // Defines the package for the Config Server application

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication // Marks this as a Spring Boot application
@EnableConfigServer // Enables Spring Cloud Config Server for centralized configuration management
public class ConfigServerApplication {

	public static void main(String[] args) {
		// Starts the Config Server application
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}

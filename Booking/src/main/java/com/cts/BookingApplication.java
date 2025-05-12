package com.cts; // Defines the package for the application

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Marks this class as a Spring Boot application
@EnableFeignClients // Enables Feign Clients for making HTTP requests to other microservices
public class BookingApplication {

    public static void main(String[] args) {
        // Starts the Spring Boot application
        SpringApplication.run(BookingApplication.class, args);
    }

}

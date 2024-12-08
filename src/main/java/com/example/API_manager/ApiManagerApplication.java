package com.example.API_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.API_manager")
public class ApiManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiManagerApplication.class, args);
    }
}


package com.examination.app;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// It tells Spring Boot to start auto-configuration, component scanning, and consider this class as a configuration class.
@SpringBootApplication
public class ExaminationApplication {

   
    // It calls SpringApplication.run to start the application.
    public static void main(String[] args) {
        SpringApplication.run(ExaminationApplication.class, args);
    }

    // This method creates a PasswordEncoder bean, specifically using BCryptPasswordEncoder.
    // The PasswordEncoder bean is used for encoding and decoding passwords securely.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.jetbrains.aplicatiequiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class AplicatieQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieQuizApplication.class, args);
	}

}

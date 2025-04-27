package com.jetbrains.aplicatiequiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableMethodSecurity
public class AplicatieQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieQuizApplication.class, args);

		// test encrypt
//		String hashedPassword = new BCryptPasswordEncoder().encode("password123");
//		System.out.println(hashedPassword);
	}

}

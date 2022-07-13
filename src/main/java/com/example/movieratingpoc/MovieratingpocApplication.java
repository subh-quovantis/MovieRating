package com.example.movieratingpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.movieratingpoc.repository")
@SpringBootApplication
public class MovieratingpocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieratingpocApplication.class, args);
	}

}

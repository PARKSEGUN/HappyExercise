package com.qkrtprjs.happyexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HappyExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyExerciseApplication.class, args);
	}

}

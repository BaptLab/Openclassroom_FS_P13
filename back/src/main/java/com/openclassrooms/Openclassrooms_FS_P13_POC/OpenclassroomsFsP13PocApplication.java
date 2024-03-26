package com.openclassrooms.Openclassrooms_FS_P13_POC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OpenclassroomsFsP13PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenclassroomsFsP13PocApplication.class, args);
	}

}

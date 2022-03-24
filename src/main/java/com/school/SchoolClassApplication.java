package com.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title= "School API", version = "1.0.0", description = "API for School"))
public class SchoolClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolClassApplication.class, args);
	}

}

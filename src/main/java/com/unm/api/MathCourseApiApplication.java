package com.unm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Import these two lines for Swagger/OpenAPI
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title = "Course Management API", 
    version = "1.0", 
    description = "API for managing instructors, courses, and students."
))
// All other @ComponentScan, @EntityScan, and @EnableJpaRepositories annotations have been removed.
// @SpringBootApplication is all you need now.
public class MathCourseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MathCourseApiApplication.class, args);
    }
}
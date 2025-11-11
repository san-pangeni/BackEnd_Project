package com.unm.api.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("course-management-api")
                .pathsToMatch("/api/**") // This tells Swagger to scan all paths under /api/
                .build();
    }
}
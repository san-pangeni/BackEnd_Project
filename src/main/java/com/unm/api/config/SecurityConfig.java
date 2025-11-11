package com.unm.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/swagger-resources"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // 1. Permit all requests to the Swagger UI and API docs
                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                
                // 2. Permit all requests to your API (for testing)
                .requestMatchers("/api/**").permitAll()
                
                // 3. All other requests can be permitted for now
                .anyRequest().permitAll()
            )
            // 4. (NEW) Explicitly disable the Form Login page you are seeing
            .formLogin(form -> form.disable())
            
            // 5. (NEW) Disable HttpBasic auth
            .httpBasic(basic -> basic.disable())
            
            // 6. (NEW) Disable CSRF, which can interfere with API testing
            .csrf(csrf -> csrf.disable()); 

        return http.build();
    }
}
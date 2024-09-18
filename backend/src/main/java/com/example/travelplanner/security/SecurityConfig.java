package com.travelplanner.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF since we are not using sessions for stateless REST API
                .csrf(csrf -> csrf.disable())
                // Permit all requests to certain endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Public endpoint
                        .anyRequest().authenticated() // Protect other endpoints
                )
                // Disable session management (stateless for API)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    // Optional: Disable security for certain endpoints if needed
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/api/users");
    }
}

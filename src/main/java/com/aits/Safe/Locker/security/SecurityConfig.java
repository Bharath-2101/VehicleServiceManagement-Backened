package com.aits.Safe.Locker.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
 
@Configuration
public class SecurityConfig {
 
    @Autowired private JwtFilter jwtFilter;
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	 http
         .csrf(csrf -> csrf.disable())
         .cors(cors -> cors.configurationSource(request -> {
             CorsConfiguration config = new CorsConfiguration();
             config.setAllowedOrigins(List.of("http://localhost:5173"));
             config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
             config.setAllowedHeaders(List.of("*"));
             config.setAllowCredentials(true);
             return config;
         }))
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔹 Stateless session
         .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/advisor/**").hasAnyRole("SERVICE_ADVISOR", "ADMIN")
            .anyRequest().authenticated()
                 ).
            addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
 
 
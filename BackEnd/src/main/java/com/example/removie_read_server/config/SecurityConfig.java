package com.example.removie_read_server.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;


@Configuration
public class SecurityConfig {

    @Value("${allowed.ip}")
    private String allowedIp;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/movie/**").permitAll()
                        .requestMatchers("/cinema/**").permitAll()
                        .requestMatchers("/release/**").permitAll()
                        .requestMatchers("/update/**").access(ipAddressAuthorization()) 
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    private AuthorizationManager<RequestAuthorizationContext> ipAddressAuthorization() {
        return (authentication, context) -> {
            HttpServletRequest request = context.getRequest();
            String remoteAddr = request.getRemoteAddr();

            boolean allowed = remoteAddr.equals(allowedIp);
            return new AuthorizationDecision(allowed);
        };
    }
}
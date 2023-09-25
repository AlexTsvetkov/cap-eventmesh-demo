package com.sap.hanesbrand.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@Order(1)
@Profile("default")
public class LocalSecurityConfig {

    @Bean
    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(AntPathRequestMatcher.antMatcher("/**"))
                .csrf(c -> c.disable()) // don't insist on csrf tokens in put, post etc.
                .authorizeHttpRequests(r -> r.anyRequest().permitAll())
                .build();
    }

}

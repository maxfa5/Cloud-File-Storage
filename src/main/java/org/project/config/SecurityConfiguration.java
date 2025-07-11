package org.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String SIGNIN_ENTRY_POINT = "/api/v1/auth/signin";
    private static final String SIGNUP_ENTRY_POINT = "/api/v1/auth/signup";
    private static final String SWAGGER_ENTRY_POINT = "/v3/api-docs/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(new AntPathRequestMatcher(SIGNIN_ENTRY_POINT)).permitAll()
                .requestMatchers(new AntPathRequestMatcher(SIGNUP_ENTRY_POINT)).permitAll()
                .requestMatchers(new AntPathRequestMatcher(SWAGGER_ENTRY_POINT)).permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .csrf(csrf -> csrf.disable());
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
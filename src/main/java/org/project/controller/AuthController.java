package org.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.project.DTO.UserRegistrationDto;
import org.project.DTO.AuthResponse;
import org.project.service.UserService;
import org.project.service.AuthenticationService;
import org.project.mapper.AuthResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private static final String SIGNIN_SUCCESS = "User signed in successfully";
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@Valid @RequestBody UserRegistrationDto registrationDto) {

        userService.register(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }


   
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signinUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        System.out.println("signinUser");
        log.info("Attempting to sign in user: {}", registrationDto.getUsername());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    registrationDto.getUsername(), 
                    registrationDto.getPassword()
                )
            );
            log.debug("Authentication successful for user: {}", registrationDto.getUsername());
        } catch (Exception e) {
            log.error("Login failed for user: {}", registrationDto.getUsername(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Login failed: " + e.getMessage()));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return ResponseEntity.ok(AuthResponseMapper.toAuthResponse(authenticationService.login(registrationDto), SIGNIN_SUCCESS));
    }

}
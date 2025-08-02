package org.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import org.project.DTO.UserRegistrationDto;
import org.project.service.UserService;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@Valid @RequestBody UserRegistrationDto registrationDto, 
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body("Validation failed: " + errorMessage.toString());
        }
        
        userService.register(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signinUser(@Valid @RequestBody UserRegistrationDto registrationDto,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body("Validation failed: " + errorMessage.toString());
        }
        
        userService.login(registrationDto);
        return ResponseEntity.ok("User signed in successfully");
    }

}
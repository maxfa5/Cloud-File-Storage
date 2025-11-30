package org.project.controller;

import java.sql.Time;
import java.util.List;
import java.util.Random;

import org.project.model.User;
import org.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

@RestController()
@RequestMapping("/api/v1/test")
@NoArgsConstructor
public class TestController {

    @Autowired
    private  UserService userService;
    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("This is a public endpoint");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok("Hello " + username + "! This is a protected endpoint");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok("Hello " + username + "! This is an admin endpoint");
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>>getAllUsers(){
        return ResponseEntity.ok(userService.getAllusers());
    }
} 
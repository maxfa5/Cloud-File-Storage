package org.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtUtils jwtUtils;

    @Autowired
    public JwtService(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public String generateAccessToken(String username) {
        return jwtUtils.generateToken(username);
    }

    public String generateRefreshToken(String username) {
        return jwtUtils.generateRefreshToken(username);
    }
    
}

package org.project.service;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public JwtUtils() {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();    
    }

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)).signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)).signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }
}

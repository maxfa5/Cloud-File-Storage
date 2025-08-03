package org.project.service;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.project.repository.UserRepository;
import org.project.DTO.AuthenticationResultDto;
import org.project.DTO.UserRegistrationDto;
import org.project.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    
    public AuthenticationResultDto login(UserRegistrationDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        
        final User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        final String accessToken =this.jwtService.generateAccessToken(user.getUsername());
        final String refreshToken =this.jwtService.generateRefreshToken(user.getUsername());
        final String tokenType = "Bearer";

        return new AuthenticationResultDto(accessToken, refreshToken, tokenType);

    }
}
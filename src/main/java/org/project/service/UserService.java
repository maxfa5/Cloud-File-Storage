package org.project.service;

import org.project.DTO.UserRegistrationDto;
import org.project.exception.RegistrationException;
import org.project.model.User;
import org.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRegistrationDto registrationDto)  {
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new RegistrationException("User with username '" + registrationDto.getUsername() + "' already exists");
        }
        
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
                
        userRepository.save(user);
    }

    public User login(UserRegistrationDto registrationDto) {
        User user = userRepository.findByUsername(registrationDto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(registrationDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }
}
package com.microservices.users.domain.service;

import com.microservices.users.domain.models.User;
import com.microservices.users.domain.repository.UserRepository;
import com.microservices.users.domain.service.exceptions.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserValidatorService userValidatorService;


    public User registerUser(User user){
        userValidatorService.validateEmail(user.getEmail());
        userValidatorService.validatePassword(user.getPassword());

        userRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
            throw new EmailAlreadyExistsException("El correo ya está registrado");
        });

        user.setId(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);

        // Persistir el usuario
        return userRepository.save(user);
    }
}


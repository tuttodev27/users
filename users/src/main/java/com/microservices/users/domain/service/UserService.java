package com.microservices.users.domain.service;

import com.microservices.users.domain.models.User;
import com.microservices.users.domain.repository.UserRepository;
import com.microservices.users.domain.service.exceptions.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidatorService userValidatorService;

    @Transactional
    public User registerUser(User user) {

        userValidatorService.validateEmail(user.getEmail());
        userValidatorService.validatePassword(user.getPassword());
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("El correo ya está registrado");
        }
        user.setId(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(generateToken());
        user.setActive(true);

        // Guardar el usuario
        return userRepository.save(user);
    }

    private String generateToken() {

        return UUID.randomUUID().toString();
    }
}

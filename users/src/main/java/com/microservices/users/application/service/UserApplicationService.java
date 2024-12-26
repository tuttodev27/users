package com.microservices.users.application.service;

import com.microservices.users.application.dto.UserRequestDTO;
import com.microservices.users.application.dto.UserResponseDTO;
import com.microservices.users.application.mapper.UserMapper;
import com.microservices.users.domain.models.User;
import com.microservices.users.domain.repository.UserRepository;

import com.microservices.users.domain.service.UserValidatorService;
import com.microservices.users.domain.service.exceptions.EmailAlreadyExistsException;
import com.microservices.users.insfraestructure.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserApplicationService {

    private final UserValidatorService domainService;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final JWTUtil jwtUtil;

    public UserResponseDTO registerUser(UserRequestDTO request) {

        domainService.validateEmail(request.getEmail());
        domainService.validatePassword(request.getPassword());
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya registrado");
        }
        User user = mapper.userToDomain(request);
        String token= jwtUtil.generateToken(request.getEmail());
        user.setToken(token);
        repository.save(user);

        return mapper.domainToUserResponse(user);
    }

    public void processUser(UserRequestDTO request) {
        String userEmail = request.getEmail();
        System.out.println("El correo es: "+userEmail);
    }
}



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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserValidatorService domainService;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final JWTUtil jwtUtil;


    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO request) {

        // Validar email y contraseña
        domainService.validateEmail(request.getEmail());
        domainService.validatePassword(request.getPassword());

        // Verificar si el email ya está registrado
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("El correo ya está registrado.");
        }

        // Mapear DTO a entidad de dominio
        User user = mapper.userToDomain(request);

        // Generar token y asignarlo al usuario
        user.setToken(generateToken(request.getEmail()));

        // Guardar el usuario en la base de datos
        repository.save(user);

        // Mapear entidad a DTO de respuesta
        return mapper.domainToUserResponse(user);
    }


    public void processUser(UserRequestDTO request) {
        String userEmail = request.getEmail();
        System.out.println("El correo es: " + userEmail);
    }


    private String generateToken(String email) {
        return jwtUtil.generateToken(email);
    }
}

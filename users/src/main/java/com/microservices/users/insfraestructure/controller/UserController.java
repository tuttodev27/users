package com.microservices.users.insfraestructure.controller;

import com.microservices.users.application.dto.UserRequestDTO;
import com.microservices.users.application.dto.UserResponseDTO;
import com.microservices.users.application.mapper.UserMapper;

import com.microservices.users.application.service.UserApplicationService;
import com.microservices.users.domain.service.UserService;
import com.microservices.users.domain.service.UserValidatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/users")
@Validated()
public class UserController {
    public final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO= userApplicationService.registerUser(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("mensaje", "Internal server error"));
        }
    }
}

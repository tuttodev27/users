package com.microservices.users.insfraestructure.controller;

import com.microservices.users.application.dto.UserRequestDTO;
import com.microservices.users.application.dto.UserResponseDTO;
import com.microservices.users.application.service.UserApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userApplicationService.registerUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }
}


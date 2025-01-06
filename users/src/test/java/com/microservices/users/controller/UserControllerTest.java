package com.microservices.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.users.application.dto.PhoneRequestDTO;
import com.microservices.users.application.dto.UserRequestDTO;
import com.microservices.users.application.dto.UserResponseDTO;
import com.microservices.users.application.service.UserApplicationService;
import com.microservices.users.insfraestructure.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(UserController.class)

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserApplicationService userApplicationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void registerUserSuccesfully()throws Exception{
        UserRequestDTO request = new UserRequestDTO(
                "Juan Perez",
                "juan.perez@example.com",
                "Password123",
                List.of(new PhoneRequestDTO("1234567", "1", "57"))
        );
        UserResponseDTO response = new UserResponseDTO(
                UUID.randomUUID(),
                request.getName(),
                request.getEmail(),
                Collections.emptyList(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "mockedToken",
                true
        );
        when(userApplicationService.registerUser(any(UserRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Juan Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"))
                .andExpect(jsonPath("$.token").value("mockedToken"));
    }
    @Test
    public void returnBadRequestWhenValidationFails() throws Exception{
        UserRequestDTO invalidRequest = new UserRequestDTO(
                "J",
                "invalid-email",
                "short",
                null
        );

        // Act & Assert
        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").exists());

    }
    @Test
    public void returnBadRequestWhenEmailAlreadyExists() throws Exception {
        // Arrange
        UserRequestDTO request = new UserRequestDTO(
                "Juan Perez",
                "juan.perez@example.com",
                "Password123",
                List.of(new PhoneRequestDTO("1234567", "1", "57"))
        );
        when(userApplicationService.registerUser(any(UserRequestDTO.class)))
                .thenThrow(new IllegalArgumentException("El correo ya registrado"));

        // Act & Assert
        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya registrado"));
    }
    @Test
    public void returnInternalServerErrorForUnexpectedException() throws Exception {
        // Arrange
        UserRequestDTO request = new UserRequestDTO(
                "Juan Perez",
                "juan.perez@example.com",
                "Password123",
                List.of(new PhoneRequestDTO("1234567", "1", "57"))
        );
        when(userApplicationService.registerUser(any(UserRequestDTO.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.mensaje").value("Internal server error"));
    }

}

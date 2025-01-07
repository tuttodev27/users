package com.microservices.users.controller;

import com.microservices.users.application.dto.UserRequestDTO;
import com.microservices.users.application.dto.UserResponseDTO;
import com.microservices.users.application.service.UserApplicationService;
import com.microservices.users.insfraestructure.controller.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private UserApplicationService userApplicationService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserSuccessfully() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO("John Doe", "john.doe@example.com", "Password1", null);
        UserResponseDTO response = new UserResponseDTO();
        response.setEmail(request.getEmail());

        when(userApplicationService.registerUser(request)).thenReturn(response);
        ResponseEntity<?> result = userController.registerUser(request);
        assertNotNull(result);
        assertEquals(201, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(userApplicationService).registerUser(request);
    }

    @Test

    public void handleEmailAlreadyExistsException() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO("John Doe", "john.doe@example.com", "Password1", null);
        doThrow(new RuntimeException("El correo ya registrado")).when(userApplicationService).registerUser(request);

        // Act
        ResponseEntity<?> response = userController.registerUser(request);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue()); // Verifica que se devuelve un error 400
        assertTrue(response.getBody().toString().contains("El correo ya registrado")); // Verifica el mensaje de error
    }

}


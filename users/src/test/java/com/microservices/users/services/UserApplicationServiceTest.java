package com.microservices.users.services;

import com.microservices.users.application.dto.UserRequestDTO;
import com.microservices.users.application.dto.UserResponseDTO;
import com.microservices.users.application.mapper.UserMapper;
import com.microservices.users.application.service.UserApplicationService;
import com.microservices.users.domain.models.User;
import com.microservices.users.domain.repository.UserRepository;
import com.microservices.users.domain.service.UserValidatorService;
import com.microservices.users.domain.service.exceptions.EmailAlreadyExistsException;
import com.microservices.users.insfraestructure.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserApplicationServiceTest {
    @Mock
    private UserValidatorService userValidatorService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private JWTUtil jwtUtil;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserApplicationService userApplicationService;

    private UserApplicationServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerUserSuccessfully(){
        UserRequestDTO request= new UserRequestDTO("Jhon Doe", "jhon.doe@gmail.com",
                "pablo123",null);
        User user = new User();
        user.setEmail(request.getEmail());
        UserResponseDTO response= new UserResponseDTO();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userMapper.userToDomain(request)).thenReturn(user);
        when(jwtUtil.generateToken(request.getEmail())).thenReturn("token");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.domainToUserResponse(user)).thenReturn(response);

        UserResponseDTO result= userApplicationService.registerUser(request);

        assertNotNull(result);
        verify(userValidatorService).validateEmail(request.getEmail());
        verify(userValidatorService).validatePassword(request.getPassword());
        verify(userRepository).save(user);
    }
    @Test
    public void ThrowExceptionWhenEmailAlreadyExist(){
        UserRequestDTO request= new UserRequestDTO("Jhon Doe", "jhon.doe@gmail.com",
                "pablo123",null);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class,() ->userApplicationService.registerUser(request));
        verify(userRepository, never()).save(any());
    }


}

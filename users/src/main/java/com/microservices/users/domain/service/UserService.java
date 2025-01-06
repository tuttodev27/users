package com.microservices.users.domain.service;

import com.microservices.users.domain.models.User;
import com.microservices.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user); // @PrePersist manejará las fechas
    }
}




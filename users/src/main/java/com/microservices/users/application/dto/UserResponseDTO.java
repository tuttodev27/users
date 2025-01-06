package com.microservices.users.application.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {
     UUID id;
     String name;
     String email;
     List<PhoneResponseDTO> phones;
     LocalDateTime created;
     LocalDateTime modified;
     LocalDateTime lastLogin;
     String token;
     boolean isActive;
}

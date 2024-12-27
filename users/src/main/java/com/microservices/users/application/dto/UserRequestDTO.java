package com.microservices.users.application.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserRequestDTO {
     @NotBlank(message = "el nombre no puede estar vacio")
     @Size(min = 2, max = 100, message = "el nombre debe tener entre 2 y 100 caracteres")
     String name;
     @NotBlank(message = "el correo es obligatorio")
     @Email(message = "el formato del correo es invalido")
     String email;
     @NotBlank(message = "la contraseña es obligatoria")
     @Size(min=8, message = "la contraseña debe tener al menos 8 caracteres")
     String password;
     @Valid
     List<PhoneRequestDTO> phones;
}



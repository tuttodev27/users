package com.microservices.users.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {


     @NotBlank(message = "El nombre no puede estar vacío")
     @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
     String name;


     @NotBlank(message = "El correo es obligatorio")
     @Email(message = "El formato del correo es inválido")
     String email;


     @NotBlank(message = "La contraseña es obligatoria")
     @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
     String password;

     @Valid
     List<PhoneRequestDTO> phones;
}



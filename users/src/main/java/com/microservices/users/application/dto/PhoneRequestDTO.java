package com.microservices.users.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO para representar un teléfono asociado a un usuario.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneRequestDTO {


    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "\\d{7,15}", message = "El número debe contener entre 7 y 15 dígitos")
    String number;


    @NotBlank(message = "El código del país es obligatorio")
    @Pattern(regexp = "\\d{1,4}", message = "El código del país debe contener entre 1 y 4 dígitos")
    String countryCode;


    @NotBlank(message = "El código de la ciudad es obligatorio")
    @Pattern(regexp = "\\d{1,4}", message = "El código de la ciudad debe contener entre 1 y 4 dígitos")
    String cityCode;
}

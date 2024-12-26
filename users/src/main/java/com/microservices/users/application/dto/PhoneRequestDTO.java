package com.microservices.users.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneRequestDTO {
    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^\\d+$", message = "El número de teléfono solo puede contener dígitos")
    String number;
    @NotBlank(message = "El código del país es obligatorio")
    @Pattern(regexp = "^\\d+$", message = "El código del país solo puede contener dígitos")
    String cityCode;

    @NotBlank(message = "El código de la ciudad es obligatorio")
    @Pattern(regexp = "^\\d+$", message = "El código de la ciudad solo puede contener dígitos")
    String countryCode;
}

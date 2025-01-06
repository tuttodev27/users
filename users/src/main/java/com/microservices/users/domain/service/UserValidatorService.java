package com.microservices.users.domain.service;

import com.microservices.users.domain.service.exceptions.InvalidEmailException;
import com.microservices.users.domain.service.exceptions.InvalidPasswordException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserValidatorService {

    private static final String EMAIL_PATTERN = "^[\\w-.]+@[\\w-]+\\.[a-z]{2,4}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

    private static final String INVALID_EMAIL_MESSAGE = "El formato del correo electrónico es inválido. Debe seguir el patrón: usuario@dominio.com";
    private static final String INVALID_PASSWORD_MESSAGE = "La contraseña es inválida. Debe contener al menos 8 caracteres, incluyendo una letra mayúscula, minúscula y un número.";

    public void validateEmail(String email) {
        if (!StringUtils.hasText(email) || !email.matches(EMAIL_PATTERN)) {
            throw new InvalidEmailException(INVALID_EMAIL_MESSAGE);
        }
    }
    public void validatePassword(String password) {
        if (!StringUtils.hasText(password) || !password.matches(PASSWORD_PATTERN)) {
            throw new InvalidPasswordException(INVALID_PASSWORD_MESSAGE);
        }
    }
}

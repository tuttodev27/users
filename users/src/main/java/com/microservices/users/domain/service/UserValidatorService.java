package com.microservices.users.domain.service;

import com.microservices.users.domain.service.exceptions.InvalidEmailException;
import com.microservices.users.domain.service.exceptions.InvalidPasswordException;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorService {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

    public void validateEmail(String email) {
        if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException("\"El formato del correo electrónico es inválido. Debe seguir el patrón: usuario@dominio.com");
        }
    }

    public void validatePassword(String password) {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new InvalidPasswordException("la contraseña es invalida. Debe contener al menos 8 caracteres, incluyendo una letra mayuscula, minuscula y un numero");
        }
    }
}
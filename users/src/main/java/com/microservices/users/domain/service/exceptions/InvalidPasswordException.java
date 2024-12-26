package com.microservices.users.domain.service.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException (String message){
        super(message);
    }
}

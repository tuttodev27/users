package com.microservices.users.domain.service.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException (String message){
        super(message);
}
}

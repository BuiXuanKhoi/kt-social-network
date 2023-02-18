package com.example.socialnetworkproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongCredentialException extends RuntimeException{

    public WrongCredentialException(String message) {
        super(message);
    }
}

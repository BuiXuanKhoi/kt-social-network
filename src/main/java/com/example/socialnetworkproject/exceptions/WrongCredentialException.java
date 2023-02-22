package com.example.socialnetworkproject.exceptions;

public class WrongCredentialException extends RuntimeException{

    public WrongCredentialException(String message) {
        super(message);
    }
}

package com.example.socialnetworkproject.exception;

import lombok.Getter;

@Getter
public class WrongInformationException extends RuntimeException{
    private String message;
    public WrongInformationException(String message){
        super();
        this.message = message;
    }
}

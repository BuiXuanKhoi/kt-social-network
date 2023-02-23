package com.example.socialnetworkproject.exception;

import com.example.socialnetworkproject.models.entities.DTO.respond.ErrorMessage;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.catalina.webresources.ExtractingRoot;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongInformationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAccountExistException(WrongInformationException exception, WebRequest request) {
        return new ErrorMessage( exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundAccountException(UsernameNotFoundException exception, WebRequest request){
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request){
        return new ErrorMessage(exception.getMessage());
    }
}

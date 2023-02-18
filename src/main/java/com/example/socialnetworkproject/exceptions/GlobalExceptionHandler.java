package com.example.socialnetworkproject.exceptions;

import com.example.socialnetworkproject.models.entities.DTO.responds.ErrorRespond;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ErrorRespond handleResourceNotFoundException(ResourceNotFoundException exception){
        String message = exception.getMessage();
        int statusCode = HttpStatus.NOT_FOUND.value();
        return new ErrorRespond(statusCode, message);
    }

    @ExceptionHandler({WrongCredentialException.class})
    protected ErrorRespond handleWrongCredentialException(WrongCredentialException exception){
        String message = exception.getMessage();
        int statusCode = HttpStatus.UNAUTHORIZED.value();
        return new ErrorRespond(statusCode, message);
    }
}

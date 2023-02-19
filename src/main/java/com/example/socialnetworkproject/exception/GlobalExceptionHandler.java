package com.example.socialnetworkproject.exception;

import com.example.socialnetworkproject.models.entities.DTO.reply.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongInformationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAccountExistException(WrongInformationException ex, WebRequest request) {

        return new ErrorMessage( ex.getMessage());
    }

}

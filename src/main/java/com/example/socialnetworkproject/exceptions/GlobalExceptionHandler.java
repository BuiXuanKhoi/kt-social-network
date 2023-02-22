package com.example.socialnetworkproject.exceptions;

import com.example.socialnetworkproject.models.entities.DTO.responds.ErrorRespond;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorRespond handleResourceNotFoundException(ResourceNotFoundException exception){
        String message = exception.getMessage();
        int statusCode = HttpStatus.NOT_FOUND.value();
        return new ErrorRespond(statusCode, message);
    }


    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    protected ErrorRespond handleUserNameNotFoundException(UsernameNotFoundException exception){
        String message = "Username not found";
        int statusCode = HttpStatus.UNAUTHORIZED.value();
        return new ErrorRespond(statusCode, message);
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    protected ErrorRespond handleBadCredentialException(BadCredentialsException exception){
        String message = "Your password or username is wrong, please check again";
        int statusCode = HttpStatus.UNAUTHORIZED.value();
        return new ErrorRespond(statusCode, message);
    }

    @ExceptionHandler({FailedUploadException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    protected ErrorRespond handleFailedUploadException(FailedUploadException exception){
        String message = exception.getMessage();
        int statusCode = HttpStatus.UNAUTHORIZED.value();
        return new ErrorRespond(statusCode, message);
    }
}

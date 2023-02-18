package com.example.socialnetworkproject.validation;


import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;

import org.springframework.stereotype.Component;

@Component
public class Validator{
    public boolean isSignUpValid(SignUpRequest request){
        String email = request.getEmail();
        String userName = request.getUserName();
        String password = request.getPassword();
        return isNotNull(email) && isNotNull(userName) && isNotNull(password);
    }

    public Boolean isNotNull(String input){
        return input != null;
    }



}

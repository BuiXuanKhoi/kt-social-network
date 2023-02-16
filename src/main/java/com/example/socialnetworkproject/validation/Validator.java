package com.example.socialnetworkproject.validation;


import com.example.socialnetworkproject.models.entities.DTO.SignUpRequest;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class Validator{
    public boolean isSignUpValid(SignUpRequest request){
        return Optional.ofNullable(request)
                        .filter(object -> StringUtils.isNotEmpty(object.getEmail()))
                        .filter(object -> StringUtils.isNotEmpty(object.getUserName()))
                        .filter(object -> StringUtils.isNotEmpty(object.getPassword()))
                        .isPresent();
    }
}

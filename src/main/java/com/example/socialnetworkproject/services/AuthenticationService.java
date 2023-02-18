package com.example.socialnetworkproject.services;

import com.example.socialnetworkproject.models.entities.DTO.responds.LoginRespond;
import com.example.socialnetworkproject.models.entities.DTO.request.LoginRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;
import com.example.socialnetworkproject.models.entities.Users;

public interface AuthenticationService {
   Users register(SignUpRequest request);

   LoginRespond login(LoginRequest request);


}

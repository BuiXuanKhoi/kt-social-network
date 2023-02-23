package com.example.socialnetworkproject.services;

import com.example.socialnetworkproject.models.entities.DTO.request.LoginRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.PasswordResetRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.UpdatePasswordRequest;
import com.example.socialnetworkproject.models.entities.DTO.respond.LoginRespond;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
   void register(SignUpRequest request);
   LoginRespond login(LoginRequest request);
   void resetPassword(String email, String userId);
   void changePassword(String encodedId, UpdatePasswordRequest passwordRequest);
}

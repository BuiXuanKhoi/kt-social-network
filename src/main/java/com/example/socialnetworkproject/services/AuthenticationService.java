package com.example.socialnetworkproject.services;

import com.example.socialnetworkproject.models.entities.DTO.SignUpRequest;
import com.example.socialnetworkproject.models.entities.Users;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
   Users register(SignUpRequest request);
}

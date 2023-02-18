package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.models.entities.DTO.responds.LoginRespond;
import com.example.socialnetworkproject.models.entities.DTO.request.LoginRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signUp(@RequestBody SignUpRequest request){
        Users users = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @PostMapping("/login")
    public LoginRespond login(@RequestBody LoginRequest loginRequest){
        return authenticationService.login(loginRequest);
    }
}

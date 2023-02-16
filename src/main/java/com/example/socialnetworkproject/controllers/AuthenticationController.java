package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.models.entities.DTO.SignUpRequest;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request){
        Users users = authenticationService.register(request);
        if(users == null){
            return ResponseEntity.badRequest().body("Fail to register new user !!!");
        }else {
            return ResponseEntity.ok("Created Account success !!!");
        }
    }
}

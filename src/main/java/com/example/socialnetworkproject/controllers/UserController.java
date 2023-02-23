package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.models.entities.DTO.request.CreatePostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest request){
        return ResponseEntity.ok("Hello world");
    }

}

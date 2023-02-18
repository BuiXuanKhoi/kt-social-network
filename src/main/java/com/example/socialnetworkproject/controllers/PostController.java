package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.models.entities.DTO.request.CreatePostRequest;
import com.example.socialnetworkproject.models.entities.Posts;
import com.example.socialnetworkproject.services.PostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Posts> createPost(@RequestBody CreatePostRequest createPostRequest){
        return null;
    }


    @GetMapping
    public List<Posts> retrieveAllPosts(){
        return null;
    }
}

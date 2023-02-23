package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.models.entities.DTO.request.CreatePostRequest;
import com.example.socialnetworkproject.models.entities.Posts;
import com.example.socialnetworkproject.services.PostService;
import com.example.socialnetworkproject.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Posts> createPost(@RequestParam("content") String postContent,
                                            @RequestParam("visible") String postVisibleLevel,
                                            @RequestParam("isCommentAllow") Boolean isCommentAllow,
                                            @RequestParam("hashtags") List<UUID> hashtagId,
                                            @RequestParam("resources") MultipartFile[] files
    ){
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setPostContent(postContent);
        createPostRequest.setPostVisibleLevel(postVisibleLevel);
        createPostRequest.setHashtags(hashtagId);
        createPostRequest.setFiles(files);
        createPostRequest.setIsCommentAllowed(isCommentAllow);
        Posts savedPost = postService.create(createPostRequest);
        return ResponseEntity.created(null).body(savedPost);
    }


    @GetMapping("/users/{userId}")
    public List<Posts> retrieveAllPostsByUser(@PathVariable UUID userId){
        return this.postService.retrieveAllByUser(userId);
    }

    @GetMapping("/newsfeed")
    public List<Posts> retrieveNewsFeed(){
        return this.postService.retrieveNewsFeed();
    }

}

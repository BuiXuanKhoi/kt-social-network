package com.example.socialnetworkproject.services;

import com.example.socialnetworkproject.models.entities.DTO.request.CreatePostRequest;
import com.example.socialnetworkproject.models.entities.Posts;

import java.util.List;
import java.util.UUID;

public interface PostService extends BaseService<Posts> {

    Posts create(CreatePostRequest createPostRequest);
    List<Posts> retrieveAllByLocalUser();

    List<Posts> retrieveNewsFeed();

}

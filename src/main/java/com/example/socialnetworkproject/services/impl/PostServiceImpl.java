package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.models.entities.Posts;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.BaseRepository;
import com.example.socialnetworkproject.repositories.PostRepository;
import com.example.socialnetworkproject.services.PostService;
import com.example.socialnetworkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BaseServiceImpl<Posts> implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public Posts create(Posts posts) {
        Users users = userService.findLocalUser();
        return postRepository.save(posts);
    }
}

package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.models.entities.Posts;
import com.example.socialnetworkproject.repositories.BaseRepository;
import com.example.socialnetworkproject.repositories.PostRepository;
import com.example.socialnetworkproject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BaseServiceImpl<Posts> implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        super(postRepository);
        this.postRepository = postRepository;
    }

    @Override
    public Posts create(Posts posts) {
        return postRepository.save(posts);
    }
}

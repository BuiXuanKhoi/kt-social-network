package com.example.socialnetworkproject.services;

import com.example.socialnetworkproject.models.entities.Posts;

public interface PostService extends BaseService<Posts> {

    Posts create(Posts posts);
}

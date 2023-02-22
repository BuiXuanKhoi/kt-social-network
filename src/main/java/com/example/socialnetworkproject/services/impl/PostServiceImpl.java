package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.constants.PostVisibleLevel;
import com.example.socialnetworkproject.models.entities.Assets;
import com.example.socialnetworkproject.models.entities.DTO.request.CreatePostRequest;
import com.example.socialnetworkproject.models.entities.Posts;
import com.example.socialnetworkproject.models.entities.Relations;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.BaseRepository;
import com.example.socialnetworkproject.repositories.PostRepository;
import com.example.socialnetworkproject.services.AssetService;
import com.example.socialnetworkproject.services.PostService;
import com.example.socialnetworkproject.services.UserService;
import com.example.socialnetworkproject.utils.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends BaseServiceImpl<Posts> implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final AssetService assetService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService, AssetService assetService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userService = userService;
        this.assetService = assetService;
    }

    @Override
    public Posts create(CreatePostRequest createPostRequest) {
        Users users = userService.findLocalUser();
        String expectVisibleLevel = createPostRequest.getPostVisibleLevel();
        PostVisibleLevel postVisibleLevel = PostVisibleLevel.of(expectVisibleLevel);

        MultipartFile[] attechedFile = createPostRequest.getFiles();
        List<Assets> assets = assetService.uploadAll(attechedFile);

        Posts posts = new Posts();
        BeanUtils.copyProperties(createPostRequest, posts);

        posts.setAuthor(users);
        posts.setPostVisibleLevel(postVisibleLevel);
        posts.setAssets(assets);
        return postRepository.save(posts);
    }

    @Override
    public List<Posts> retrieveAllByLocalUser() {
        Users users = userService.findLocalUser();
        return users.getPosts()
                    .stream()
                    .sorted(Comparator.comparing(Posts::getPostId).reversed())
                    .collect(Collectors.toList());
    }

    @Override
    public List<Posts> retrieveNewsFeed() {
        Users users = userService.findLocalUser();
        return users.getRelationTo()
                    .stream()
                    .map(Relations::getRelationTo)
                    .map(Users::getPosts)
                    .flatMap(Collection::stream)
                    .sorted(Comparator.comparing(Posts::getPostId).reversed())
                    .collect(Collectors.toList());
    }
}

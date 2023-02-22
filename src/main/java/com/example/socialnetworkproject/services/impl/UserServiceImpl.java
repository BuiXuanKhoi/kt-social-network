package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.exceptions.ResourceNotFoundException;
import com.example.socialnetworkproject.models.entities.Posts;
import com.example.socialnetworkproject.models.entities.Relations;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.BaseRepository;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<Users> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Users findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(
                        () -> new ResourceNotFoundException("There's no user with that name")
                );
    }

    @Override
    public Users findLocalUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUserName(userName);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not exist User with Email : " + email)
                );
    }
}

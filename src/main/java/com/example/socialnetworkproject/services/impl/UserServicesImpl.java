package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.models.entities.CustomUserDetails;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.services.UserServices;
import com.example.socialnetworkproject.solr.SolrUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServicesImpl implements UserServices, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Not found " + username + " !!!");
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserByUserId(UUID userId) {
        Optional<Users> user = userRepository.findByUserId(userId);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Not found " + userId + " !!!");
        }
        return new CustomUserDetails(user.get());
    }
}

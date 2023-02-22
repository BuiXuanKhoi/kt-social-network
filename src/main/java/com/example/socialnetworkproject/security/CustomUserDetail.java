package com.example.socialnetworkproject.security;

import com.example.socialnetworkproject.constants.Role;
import com.example.socialnetworkproject.models.entities.Users;

import java.util.Collection;
import java.util.LinkedList;

public class CustomUserDetail {

    private String email;
    private String password;
    private LinkedList<Role> authorities;

    public CustomUserDetail(String email, String password, LinkedList<Role> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetail init(Users users){
        String email = users.getEmail();
        String password = users.getPassword();
        Role role = users.getRole();
        LinkedList<Role> roles = new LinkedList<>();
        roles.add(role);
        return new CustomUserDetail(
                email,
                password,roles
        );
    }
}

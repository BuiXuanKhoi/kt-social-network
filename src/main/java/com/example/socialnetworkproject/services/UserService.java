package com.example.socialnetworkproject.services;

import com.example.socialnetworkproject.models.entities.Users;

import java.util.List;
import java.util.UUID;

public interface UserService extends BaseService<Users> {

    Users findByUserName(String userName);

    Users findLocalUser();

    Users findByEmail(String email);


}

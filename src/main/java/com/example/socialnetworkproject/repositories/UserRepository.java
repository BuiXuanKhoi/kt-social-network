package com.example.socialnetworkproject.repositories;

import com.example.socialnetworkproject.models.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Users findByUserName(String username);
}

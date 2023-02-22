package com.example.socialnetworkproject.repositories;

import com.example.socialnetworkproject.models.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<Users> {

    @Query(value = "SELECT * FROM users WHERE users.user_name = :userName",
            nativeQuery = true)
    Optional<Users> findByUserName(String userName);

    Boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);
}

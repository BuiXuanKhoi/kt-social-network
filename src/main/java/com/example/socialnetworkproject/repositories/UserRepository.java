package com.example.socialnetworkproject.repositories;

import com.example.socialnetworkproject.models.entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Users findByUserName(String username);

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Users u WHERE u.userId = :userId")
    Optional<Users> findByUserId(@Param("userId") UUID userId);

    @Modifying
    @Query("UPDATE Users u SET u.password = :password WHERE u.userId = :userId")
    int updateEmail(@Param("password") String password, @Param("userId") UUID userId);
}

package com.example.socialnetworkproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends Object> extends JpaRepository<T, UUID> {
}

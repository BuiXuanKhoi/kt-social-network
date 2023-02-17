package com.example.socialnetworkproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<T> extends JpaRepository<T, UUID> {
}

package com.example.socialnetworkproject.repositories;

import com.example.socialnetworkproject.models.entities.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InformationRepository extends JpaRepository<Information, UUID> {
}

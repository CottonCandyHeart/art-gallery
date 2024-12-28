package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    // niestandardowe metody zapytań
}

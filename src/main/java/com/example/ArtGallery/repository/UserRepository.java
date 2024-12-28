package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // niestandardowe metody zapytań
}

package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    // niestandardowe metody zapytań
}

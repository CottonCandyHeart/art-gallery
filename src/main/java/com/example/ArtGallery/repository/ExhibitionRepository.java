package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    // niestandardowe metody zapytań
}

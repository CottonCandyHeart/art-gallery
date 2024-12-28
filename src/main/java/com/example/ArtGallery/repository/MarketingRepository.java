package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketingRepository extends JpaRepository<Marketing, Long> {
    // niestandardowe metody zapytań
}

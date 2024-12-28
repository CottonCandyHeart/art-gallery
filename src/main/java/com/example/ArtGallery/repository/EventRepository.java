package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // niestandardowe metody zapytań
}

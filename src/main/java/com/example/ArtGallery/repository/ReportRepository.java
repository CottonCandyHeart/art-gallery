package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // niestandardowe metody zapytań
}

package com.example.ArtGallery.repository;

import com.example.ArtGallery.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // niestandardowe metody zapytań
}

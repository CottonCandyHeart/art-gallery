package com.example.ArtGallery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "artwork_id", nullable = false)
    private Long artworkId;

    @Column(name = "sale_date", nullable = false)
    private String saleDate;

    @Column(name = "price", nullable = false)
    private float price;

    // GETTERS AND SETTERS

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Long artworkId) {
        this.artworkId = artworkId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

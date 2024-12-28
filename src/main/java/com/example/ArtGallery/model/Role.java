package com.example.ArtGallery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @Column(name = "salary", nullable = false)
    private float salary;

    // GETTERS AND SETTERS

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}

package com.example.ArtGallery.business.users;

public class Worker extends User {
    private String role;

    public Worker(String ID, String username, String name, String surname, String role) {
        super(ID, username, name, surname);
        this.role = role;
    }

    // ---------------- GETTERS ----------------
    public String getRole() {return role;}

    // ---------------- SETTERS ----------------
    public void setRole(String role) {this.role = role;}
}

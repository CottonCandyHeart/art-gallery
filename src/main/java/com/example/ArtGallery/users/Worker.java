package com.example.ArtGallery.users;

public class Worker extends User {
    private String role;

    public Worker(String ID, String username, String password, String name, String surname, String email, String role) {
        super(ID, username, password, name, surname, email);
        this.role = role;
    }

    // ---------------- GETTERS ----------------
    public String getRole() {return role;}

    // ---------------- SETTERS ----------------
    public void setRole(String role) {this.role = role;}
}

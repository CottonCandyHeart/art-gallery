package com.example.ArtGallery.model.users;

import com.example.ArtGallery.db.DB;

public class Worker extends User {
    private String role;

    public Worker(String ID, String username, String name, String surname, String role) {
        super(ID, username, name, surname);
        this.role = role;
    }

    // ---------------- METHODS ----------------
    @Override
    public void addUser(DB db, String password){
        // TODO szyfrowanie
        db.callProcedure("addUser", super.getID().substring(0,3), super.getUsername(), password, super.getName(), super.getSurname(), 1, role);
    }
    @Override
    public void modUser(DB db){
        db.executeUpdate(db.getSt(), "UPDATE Users SET name = \"" + super.getName() + "\", surname = \"" + super.getSurname() + "\", role = \"" + role + "\" WHERE user_id = \"" + super.getID() + "\";");
    }

    // ---------------- GETTERS ----------------
    public String getRole() {return role;}

    // ---------------- SETTERS ----------------
    public void setRole(String role) {this.role = role;}
}
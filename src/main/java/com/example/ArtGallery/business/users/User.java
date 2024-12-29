package com.example.ArtGallery.business.users;

public class User {
    private String ID;
    private String username;
    private String name;
    private String surname;

    public User(String ID, String username, String name, String surname) {
        this.ID = ID;
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    // ---------------- METHODS ----------------


    // ---------------- GETTERS ----------------
    public String getID() {return ID;}
    public String getUsername() {return username;}
    public String getName() {return name;}
    public String getSurname() {return surname;}

    // ---------------- SETTERS ----------------
    public void setID(String ID) {this.ID = ID;}
    public void setUsername(String username) {this.username = username;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
}

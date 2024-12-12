package com.ArtGallery.users;

public class User {
    private String ID;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;

    public User(String ID, String username, String password, String name, String surname, String email) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // ---------------- GETTERS ----------------
    public String getID() {return ID;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getEmail() {return email;}

    // ---------------- SETTERS ----------------
    public void setID(String ID) {this.ID = ID;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setEmail(String email) {this.email = email;}
}

package com.example.ArtGallery.business.artists;

public class Artist {
    private String name;
    private String surname;
    private String bio;
    private String birthDate;
    private String deathDate;

    public Artist(String name, String surname, String bio, String birthDate, String deathDate) {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    // ---------------- GETTERS ----------------
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getBio() {return bio;}
    public String getBirthDate() {return birthDate;}
    public String getDeathDate() {return deathDate;}

    // ---------------- SETTERS ----------------
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setBio(String bio) {this.bio = bio;}
    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}
    public void setDeathDate(String deathDate) {this.deathDate = deathDate;}
}

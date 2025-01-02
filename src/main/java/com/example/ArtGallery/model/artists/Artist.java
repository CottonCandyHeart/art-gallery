package com.example.ArtGallery.model.artists;

import com.example.ArtGallery.db.DB;

public class Artist {
    private int ID;
    private String name;
    private String surname;
    private String bio;
    private String birthDate;
    private String deathDate;

    public Artist(int ID, String name, String surname, String bio, String birthDate, String deathDate) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.bio = bio;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    // ---------------- METHODS ----------------
    public void addArtist(DB db){
        db.callProcedure("addArtist",  name, surname, bio, birthDate, deathDate);
        int id = db.getDataInt("SELECT artist_id FROM Artists WHERE name LIKE \"" + name + "\" AND surname LIKE \"" + surname + "\";");
        this.ID = id;
    }
    public void modArtist(DB db){
        db.callProcedure("modArtist",  ID, name, surname, bio, birthDate, deathDate);
    }
    public void deleteArtist(DB db){
        db.executeUpdate(db.getSt(), "DELETE FROM Artists WHERE artist_id = " + ID + ";");
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getBio() {return bio;}
    public String getBirthDate() {return birthDate;}
    public String getDeathDate() {return deathDate;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setBio(String bio) {this.bio = bio;}
    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}
    public void setDeathDate(String deathDate) {this.deathDate = deathDate;}
}

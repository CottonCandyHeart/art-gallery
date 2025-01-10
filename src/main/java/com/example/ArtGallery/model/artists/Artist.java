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
    public String getConvertedBirthDate() {
        if (birthDate == null) return "";

        String d = birthDate.substring(birthDate.lastIndexOf("-")+1, birthDate.length());
        switch (birthDate.substring(birthDate.indexOf("-")+1, birthDate.lastIndexOf("-"))){
            case "01":
                d += " styczeń ";
                break;
            case "02":
                d += " luty ";
                break;
            case "03":
                d += " marzec ";
                break;
            case "04":
                d += " kwiecień ";
                break;
            case "05":
                d += " maj ";
                break;
            case "06":
                d += " czerwiec ";
                break;
            case "07":
                d += " lipiec ";
                break;
            case "08":
                d += " sierpień ";
                break;
            case "09":
                d += " wrzesień ";
                break;
            case "10":
                d += " październik ";
                break;
            case "11":
                d += " listopad ";
                break;
            case "12":
                d += " grudzień ";
                break;
        }

        d += birthDate.substring(0, birthDate.indexOf("-"));

        return d;
    }
    public String getConvertedDeathDate() {
        if (deathDate == null) return "";

        String d = deathDate.substring(deathDate.lastIndexOf("-")+1, deathDate.length());
        switch (deathDate.substring(deathDate.indexOf("-")+1, deathDate.lastIndexOf("-"))){
            case "01":
                d += " styczeń ";
                break;
            case "02":
                d += " luty ";
                break;
            case "03":
                d += " marzec ";
                break;
            case "04":
                d += " kwiecień ";
                break;
            case "05":
                d += " maj ";
                break;
            case "06":
                d += " czerwiec ";
                break;
            case "07":
                d += " lipiec ";
                break;
            case "08":
                d += " sierpień ";
                break;
            case "09":
                d += " wrzesień ";
                break;
            case "10":
                d += " październik ";
                break;
            case "11":
                d += " listopad ";
                break;
            case "12":
                d += " grudzień ";
                break;
        }

        d += deathDate.substring(0, deathDate.indexOf("-"));

        return d;
    }

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setBio(String bio) {this.bio = bio;}
    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}
    public void setDeathDate(String deathDate) {this.deathDate = deathDate;}
}

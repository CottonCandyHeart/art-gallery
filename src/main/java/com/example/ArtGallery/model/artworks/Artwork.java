package com.example.ArtGallery.model.artworks;
import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.*;

public class Artwork {
    private int ID;
    private String title;
    private Artist artist;
    private String creationDate;
    private String method;
    private String description;
    private String location;
    private String status;
    private String picturePath;

    public Artwork(int ID, String title, Artist artist, String creationDate, String method, String description, String location, String status, String picturePath) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.creationDate = creationDate;
        this.method = method;
        this.description = description;
        this.location = location;
        this.status = status;
        this.picturePath = picturePath;
    }

    // ---------------- METHODS ----------------
    public void addArtwork(DB db){
        db.callProcedure("addArtwork", title, artist.getName(), artist.getSurname(), creationDate, method, description, location, status, picturePath);
        int id = db.getDataInt("SELECT artwork_id FROM Artworks WHERE title LIKE \"" + title + "\" AND artist_id = " + artist.getID() + ";");
        this.ID = id;
    }
    public void modArtwork(DB db){
        db.callProcedure("modArtwork",  ID, title, description, location, status, picturePath);
    }
    public void deleteArtwork(DB db){
        db.executeUpdate(db.getSt(), "DELETE FROM Artworks WHERE artwork_id = " + ID + ";");
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public String getTitle() {return title;}
    public Artist getArtist() {return artist;}
    public String getCreationDate() {return creationDate;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public String getStatus() {return status;}
    public String getPicturePath() {return picturePath;}
    public String getMethod(){return method;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setTitle(String title) {this.title = title;}
    public void setArtist(Artist artist) {this.artist = artist;}
    public void setCreationDate(String creationDate) {this.creationDate = creationDate;}
    public void setDescription(String description) {this.description = description;}
    public void setLocation(String location) {this.location = location;}
    public void setStatus(String status) {this.status = status;}
    public void setPicturePath(String picturePath) {this.picturePath = picturePath;}
    public void setMethod(String method) {this.method = method;}
}

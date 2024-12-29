package com.example.ArtGallery.business.artworks;
import com.example.ArtGallery.business.artists.*;

public class Artwork {
    private Long ID;
    private String title;
    private Artist artist;
    private String creationDate;
    private String description;
    private String location;
    private String status;
    private String attribute;

    public Artwork(Long ID, String title, Artist artist, String creationDate, String description, String location, String status, String attribute) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.creationDate = creationDate;
        this.description = description;
        this.location = location;
        this.status = status;
        this.attribute = attribute;
    }

    // ---------------- GETTERS ----------------
    public Long getID() {return ID;}
    public String getTitle() {return title;}
    public Artist getArtist() {return artist;}
    public String getCreationDate() {return creationDate;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public String getStatus() {return status;}
    public String getAttribute() {return attribute;}

    // ---------------- SETTERS ----------------
    public void setID(Long ID) {this.ID = ID;}
    public void setTitle(String title) {this.title = title;}
    public void setArtist(Artist artist) {this.artist = artist;}
    public void setCreationDate(String creationDate) {this.creationDate = creationDate;}
    public void setDescription(String description) {this.description = description;}
    public void setLocation(String location) {this.location = location;}
    public void setStatus(String status) {this.status = status;}
    public void setAttribute(String attribute) {this.attribute = attribute;}
}

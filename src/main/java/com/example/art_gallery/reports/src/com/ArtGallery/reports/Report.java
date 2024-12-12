package com.ArtGallery.reports;

public class Report {
    private int ID;
    private String type;
    private String generationDate;
    private String details;

    public Report(int ID, String type, String generationDate, String details) {
        this.ID = ID;
        this.type = type;
        this.generationDate = generationDate;
        this.details = details;
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public String getType() {return type;}
    public String getGenerationDate() {return generationDate;}
    public String getDetails() {return details;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setType(String type) {this.type = type;}
    public void setGenerationDate(String generationDate) {this.generationDate = generationDate;}
    public void setDetails(String details) {this.details = details;}
}

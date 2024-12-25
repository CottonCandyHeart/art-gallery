package com.example.ArtGallery.marketing;

public class Promotion {
    private int ID;
    private String content;
    private String targetAudience;
    private String startDate;
    private String endDate;

    public Promotion(int ID, String content, String targetAudience, String startDate, String endDate) {
        this.ID = ID;
        this.content = content;
        this.targetAudience = targetAudience;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public String getContent() {return content;}
    public String getTargetAudience() {return targetAudience;}
    public String getStartDate() {return startDate;}
    public String getEndDate() {return endDate;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setContent(String content) {this.content = content;}
    public void setTargetAudience(String targetAudience) {this.targetAudience = targetAudience;}
    public void setStartDate(String startDate) {this.startDate = startDate;}
    public void setEndDate(String endDate) {this.endDate = endDate;}
}

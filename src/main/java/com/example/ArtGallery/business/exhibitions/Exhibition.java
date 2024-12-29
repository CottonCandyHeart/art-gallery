package com.example.ArtGallery.business.exhibitions;

public class Exhibition {
    private int iD;
    private String name;
    private String startDate;
    private String endDate;
    private String location;
    private String description;

    public Exhibition(int iD, String name, String startDate, String endDate, String location, String description) {
        this.iD = iD;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
    }

    // ---------------- GETTERS ----------------
    public int getiD() {return iD;}
    public String getName() {return name;}
    public String getStartDate() {return startDate;}
    public String getEndDate() {return endDate;}
    public String getLocation() {return location;}
    public String getDescription() {return description;}

    // ---------------- SETTERS ----------------
    public void setiD(int iD) {this.iD = iD;}
    public void setName(String name) {this.name = name;}
    public void setStartDate(String startDate) {this.startDate = startDate;}
    public void setEndDate(String endDate) {this.endDate = endDate;}
    public void setLocation(String location) {this.location = location;}
    public void setDescription(String description) {this.description = description;}
}

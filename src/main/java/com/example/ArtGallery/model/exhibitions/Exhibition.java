package com.example.ArtGallery.model.exhibitions;

import com.example.ArtGallery.db.DB;

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

    // ---------------- METHODS ----------------
    public void addExhibition(DB db){
        db.callProcedure("addExhibition", name, startDate, endDate, location, description);
        int id = db.getDataInt("SELECT exhibition_id FROM Exhibition WHERE name LIKE \"" + name + "\" AND start_date = \'" + startDate + "\';");
        this.iD = id;
    }
    public void modExhibition(DB db){
        db.callProcedure("modExhibition", iD, name, startDate, endDate, location, description);
    }
    public void deleteExhibition(DB db){
        db.executeUpdate(db.getSt(), "DELETE FROM Exhibition WHERE exhibition_id = " + iD + ";");
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

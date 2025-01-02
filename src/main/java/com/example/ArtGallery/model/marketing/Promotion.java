package com.example.ArtGallery.model.marketing;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;

public class Promotion {
    private int ID;
    private String content;
    private String targetAudience;
    private String startDate;
    private String endDate;
    private User manager;

    public Promotion(int ID, String content, String targetAudience, String startDate, String endDate, User manager) {
        this.ID = ID;
        this.content = content;
        this.targetAudience = targetAudience;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manager = manager;
    }

    // ---------------- METHODS ----------------
    public void addPromotion(DB db){
        db.callProcedure("addMarketing", content, targetAudience, startDate, endDate, manager.getID());
        int id = db.getDataInt("SELECT promotion_id FROM Marketing WHERE content LIKE \"" + content + "\" AND start_date = \'" + startDate + "\';");
        this.ID = id;
    }
    public void modPromotion(DB db){
        db.callProcedure("modMarketing", ID, content, targetAudience, startDate, endDate, manager.getID());
    }
    public void deletePromotion(DB db){
        db.executeUpdate(db.getSt(), "DELETE FROM Marketing WHERE promotion_id = " + ID + ";");
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

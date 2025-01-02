package com.example.ArtGallery.model.events;
import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.exhibitions.Exhibition;

import java.time.ZonedDateTime;

public class Event {
    private int ID;
    private String name;
    private ZonedDateTime eventDate;
    private Exhibition exhibition;
    private int capacity;
    private String type;

    public Event(int ID, String name, ZonedDateTime eventDate, Exhibition exhibition, int capacity, String type) {
        this.ID = ID;
        this.name = name;
        this.eventDate = eventDate;
        this.exhibition = exhibition;
        this.capacity = capacity;
        this.type = type;
    }

    // ---------------- METHODS ----------------
    public void addEvent(DB db){
        db.callProcedure("addEvent", name, eventDate, exhibition.getiD(), capacity, type);
        int id = db.getDataInt("SELECT event_id FROM Events WHERE name LIKE \"" + name + "\" AND event_date = \"" +eventDate + "\";");
        this.ID = id;
    }
    public void modEvent(DB db){
        db.callProcedure("modEvent",  ID, name, eventDate, exhibition.getiD(), capacity, type);
    }
    public void deleteEvent(DB db){
        db.executeUpdate(db.getSt(), "DELETE FROM Events WHERE event_id = " + ID + ";");
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public String getName() {return name;}
    public ZonedDateTime getEventDate() {return eventDate;}
    public Exhibition getExhibition() {return exhibition;}
    public int getCapacity() {return capacity;}
    public String getType() {return type;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setEventDate(ZonedDateTime eventDate) {this.eventDate = eventDate;}
    public void setExhibition(Exhibition exhibition) {this.exhibition = exhibition;}
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public void setType(String type) {this.type = type;}

    @Override
    public String toString() {
        return "Events{" +
                "date=" + eventDate +
                ", name='" + name + '\'' +
                ", exhibition=" + exhibition + '\'' +
                ", capacity=" + capacity + '\'' +
                ", type=" + type +
                '}';
    }

}

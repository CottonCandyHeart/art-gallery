package com.example.ArtGallery.events;
import com.example.ArtGallery.exhibitions.Exhibition;

public class Event {
    private int ID;
    private String name;
    private String eventDate;
    private Exhibition exhibition;
    private int capacity;
    private String type;

    public Event(int ID, String name, String eventDate, Exhibition exhibition, int capacity, String type) {
        this.ID = ID;
        this.name = name;
        this.eventDate = eventDate;
        this.exhibition = exhibition;
        this.capacity = capacity;
        this.type = type;
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public String getName() {return name;}
    public String getEventDate() {return eventDate;}
    public Exhibition getExhibition() {return exhibition;}
    public int getCapacity() {return capacity;}
    public String getType() {return type;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setEventDate(String eventDate) {this.eventDate = eventDate;}
    public void setExhibition(Exhibition exhibition) {this.exhibition = exhibition;}
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public void setType(String type) {this.type = type;}
}

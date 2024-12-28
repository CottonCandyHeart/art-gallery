package com.example.ArtGallery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "event_date", nullable = false)
    private String eventDate;

    @Column(name = "exhibition_id")
    private Long exhibitionId;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    // GETTERS AND SETTERS

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}

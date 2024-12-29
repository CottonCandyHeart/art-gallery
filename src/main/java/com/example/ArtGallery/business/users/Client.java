package com.example.ArtGallery.business.users;

public class Client extends User {
    private int phoneNo;

    public Client(String ID, String username, String name, String surname, int phoneNo) {
        super(ID, username, name, surname);
        this.phoneNo = phoneNo;
    }

    // ---------------- GETTERS ----------------
    public int getPhoneNo() {return phoneNo;}

    // ---------------- SETTERS ----------------
    public void setPhoneNo(int phoneNo) {this.phoneNo = phoneNo;}
}

package com.ArtGallery.users;

public class Client extends User {
    private int phoneNo;

    public Client(String ID, String username, String password, String name, String surname, String email, int phoneNo) {
        super(ID, username, password, name, surname, email);
        this.phoneNo = phoneNo;
    }

    // ---------------- GETTERS ----------------
    public int getPhoneNo() {return phoneNo;}

    // ---------------- SETTERS ----------------
    public void setPhoneNo(int phoneNo) {this.phoneNo = phoneNo;}
}

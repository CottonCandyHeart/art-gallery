package com.example.ArtGallery.model.users;

import com.example.ArtGallery.db.DB;

public class Client extends User {
    private int phoneNo;

    public Client(String ID, String username, String name, String surname, int phoneNo) {
        super(ID, username, name, surname);
        this.phoneNo = phoneNo;
    }

    // ---------------- METHODS ----------------
    @Override
    public void addUser(DB db, String password, String userType){
        // TODO szyfrowanie
        db.callProcedure("addUser", userType, super.getUsername(), password, super.getName(), super.getSurname(), phoneNo, "NULL");
        String id = db.getDataString("SELECT user_id FROM Users WHERE username LIKE \"" + super.getUsername() + "\";");
        super.setID(id);
    }
    @Override
    public void modUser(DB db){
        db.executeUpdate(db.getSt(), "UPDATE Users SET name = \"" + super.getName() + "\", surname = \"" + super.getSurname() + "\", phoneNo = " + phoneNo + " WHERE user_id = \"" + super.getID() + "\";");
    }


    // ---------------- GETTERS ----------------
    public int getPhoneNo() {return phoneNo;}

    // ---------------- SETTERS ----------------
    public void setPhoneNo(int phoneNo) {this.phoneNo = phoneNo;}
}

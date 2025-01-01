package com.example.ArtGallery.model.users;

import com.example.ArtGallery.db.DB;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String ID;
    private String username;
    private String name;
    private String surname;

    public User(String ID, String username, String name, String surname) {
        this.ID = ID;
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    // ---------------- METHODS ----------------
    public void addUser(DB db, String password, String userType){}
    public void modUser(DB db){}
    public void modPasswd(DB db, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        db.executeUpdate(db.getSt(), "UPDATE Users SET password = \"" + hashedPassword + "\" WHERE user_id = \"" + ID + "\";");
    }
    public void deleteUser(DB db){
        db.executeUpdate(db.getSt(), "DELETE FROM Users WHERE user_id = \"" + ID + "\";");
    }
    // ---------------- GETTERS ----------------
    public String getID() {return ID;}
    public String getUsername() {return username;}
    public String getName() {return name;}
    public String getSurname() {return surname;}

    // ---------------- SETTERS ----------------
    public void setID(String ID) {this.ID = ID;}
    public void setUsername(String username) {this.username = username;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
}

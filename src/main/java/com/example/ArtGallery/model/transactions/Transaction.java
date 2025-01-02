package com.example.ArtGallery.model.transactions;
import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.*;
import com.example.ArtGallery.model.artworks.*;

public class Transaction {
    private int ID;
    private Client client;
    private Artwork artwork;
    private String saleDate;
    private float price;

    public Transaction(int ID, Client client, Artwork artwork, String saleDate, float price) {
        this.ID = ID;
        this.client = client;
        this.artwork = artwork;
        this.saleDate = saleDate;
        this.price = price;
    }

    // ---------------- METHODS ----------------
    public void addTransaction(DB db){
        db.callProcedure("makeTransaction", client.getID(), artwork.getID());
        int id = db.getDataInt("SELECT transaction_id FROM Transactions WHERE user_id = " + client.getID() + " AND artwork_id = " + artwork.getID() + ";");
        this.ID = id;
    }
    public void deleteTransaction(DB db) {
        db.executeUpdate(db.getSt(), "DELETE FROM Transactions WHERE transaction_id = " + ID + ";");
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public Client getClient() {return client;}
    public Artwork getArtwork() {return artwork;}
    public String getSaleDate() {return saleDate;}
    public float getPrice() {return price;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setClient(Client client) {this.client = client;}
    public void setArtwork(Artwork artwork) {this.artwork = artwork;}
    public void setSaleDate(String saleDate) {this.saleDate = saleDate;}
    public void setPrice(float price) {this.price = price;}
}

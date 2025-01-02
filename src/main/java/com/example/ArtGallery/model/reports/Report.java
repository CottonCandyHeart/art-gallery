package com.example.ArtGallery.model.reports;

import com.example.ArtGallery.db.*;

import java.sql.ResultSet;

public class Report {
    private int ID;
    private String type;
    private String generationDate;
    private String details;

    public Report(int ID, String type, String generationDate, String details) {
        this.ID = ID;
        this.type = type;
        this.generationDate = generationDate;
        this.details = details;
    }

    // ---------------- METHODS ----------------
    public void generateFinancialReport(DB db, String startDate, String endDate, String workerID){ // 2025-01-01
        String report = "";
        String sql = "SELECT transaction_id, user_id, title, CONCAT(Artists.name, \" \", Artists.surname), sale_date, price, income FROM Transactions NATURAL JOIN Artworks JOIN Artists ON Artworks.artist_id = Artists.artist_id WHERE sale_date BETWEEN '" + startDate + "' AND '" + endDate + "';";
        ResultSet r = db.executeQuery(db.getSt(), sql);
        if (r == null){
            report = "Brak dostępnych tranzakcji w okresie " + startDate + " - " + endDate;
        } else {
            report = db.sqlGetDataByName(r);
            report += "\n" + db.getDataDouble("SELECT SUM(income) FROM Transactions WHERE sale_date BETWEEN '" + startDate + "' AND '" + endDate + "';");
        }

        db.callProcedure("addReport", "FINANCIAL REPORT", report, workerID);


    }
    public void generateEventReport(DB db, String startDate, String endDate){
        // RAPORT Z ILOŚCI OSÓB BIORĄCYCH UDZIAŁ W WYDARZENIU
        // Nową tabela
    }
    public void generateCollectionReport(DB db){
        // Stan konserwacji dzieł:
        //Lista dzieł wymagających renowacji, z podziałem na kategorie.
        //Popularność eksponatów:
        //Dane o liczbie odwiedzających oglądających poszczególne dzieła.
        //Raport wypożyczeń
        //Informacje o dziełach wypożyczonych innym instytucjom, z datami i warunkami.
    }

    // ---------------- GETTERS ----------------
    public int getID() {return ID;}
    public String getType() {return type;}
    public String getGenerationDate() {return generationDate;}
    public String getDetails() {return details;}

    // ---------------- SETTERS ----------------
    public void setID(int ID) {this.ID = ID;}
    public void setType(String type) {this.type = type;}
    public void setGenerationDate(String generationDate) {this.generationDate = generationDate;}
    public void setDetails(String details) {this.details = details;}
}

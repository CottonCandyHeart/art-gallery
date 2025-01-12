package com.example.ArtGallery.model.reports;

import com.example.ArtGallery.db.*;

import java.sql.ResultSet;
import java.util.List;

public class Report {

    // ---------------- METHODS ----------------
    public void generateFinancialReport(DB db, String startDate, String endDate, String workerID){ // 2025-01-01
        System.out.println(startDate + " --- " + endDate);
        String report = "";
        String sql = "SELECT transaction_id, user_id, title, CONCAT(Artists.name, \" \", Artists.surname) as artist_name, sale_date, price, income FROM Transactions NATURAL JOIN Artworks JOIN Artists ON Artworks.artist_id = Artists.artist_id WHERE sale_date BETWEEN '" + startDate + "' AND '" + endDate + "';";
        ResultSet r = db.executeQuery(db.getSt(), sql);
        if (r == null){
            report = "Brak dostępnych tranzakcji w okresie " + startDate + " - " + endDate;
        } else {
            report = db.sqlGetDataByName(r);
            report += "\n" + db.getDataDouble("SELECT SUM(income) FROM Transactions WHERE sale_date BETWEEN '" + startDate + "' AND '" + endDate + "';");
        }

        db.callProcedure("addReport", "FINANCIAL REPORT", report, workerID);


    }
    public void generateEventReport(DB db, String startDate, String endDate, String workerID){
        String report = "";
        List<Integer> result = db.getDataIntList("SELECT DISTINCT(event_id) FROM EventReservations WHERE reservationDate BETWEEN '" + startDate + "' AND '" + endDate + "';");

        for (int r : result){
            int count = db.getDataInt("SELECT COUNT(*) FROM EventReservations WHERE event_id =" + result);
            report += r + " - " + count + " uczestników; \n";
        }

        db.callProcedure("addReport", "EVENT REPORT", report, workerID);

    }
    public void generateExhibitionReport(DB db, String startDate, String endDate, String workerID){
        String report = "";
        String sql = "SELECT exhibitionn_id, name, location FROM Exhibitions WHERE start_date BETWEEN '" + startDate + "' AND '" + endDate + "';";
        ResultSet r = db.executeQuery(db.getSt(), sql);
        if (r == null){
            report = "Brak dostępnych tranzakcji w okresie " + startDate + " - " + endDate;
        } else {
            report = db.sqlGetDataByName(r);
        }

        db.callProcedure("addReport", "EXHIBITION REPORT", report, workerID);
    }

}

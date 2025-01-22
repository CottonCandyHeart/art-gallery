package com.example.ArtGallery.model.reports;

import com.example.ArtGallery.db.*;

import java.sql.ResultSet;
import java.util.List;

public class Report {

    // ---------------- METHODS ----------------
    public String generateFinancialReport(DB db, String startDate, String endDate, String workerID){ // 2025-01-01
        System.out.println(startDate + " --- " + endDate);
        String report = "";
        String sql = "SELECT transaction_id, user_id, title, CONCAT(Artists.name, \" \", Artists.surname) as artist_name, sale_date, price, income FROM Transactions NATURAL JOIN Artworks JOIN Artists ON Artworks.artist_id = Artists.artist_id WHERE sale_date BETWEEN '" + startDate + "' AND '" + endDate + "';";
        ResultSet r = db.executeQuery(db.getSt(), sql);
        if (r == null){
            report = "Brak dostępnych tranzakcji w okresie " + startDate + " - " + endDate;
        } else {
            report = db.sqlGetDataByName(r);
            report += "\nINCOME SUM:\t" + db.getDataDouble("SELECT SUM(income) FROM Transactions WHERE sale_date BETWEEN '" + startDate + "' AND '" + endDate + "';") + " PLN";
        }

        db.callProcedure("addReport", "FINANCIAL REPORT", report, workerID);
        return db.getDataString("SELECT details FROM Reports ORDER BY report_id DESC LIMIT 1;");
    }
    public String generateEventReport(DB db, String startDate, String endDate, String workerID){
        String report = "";
        List<Integer> result = db.getDataIntList("SELECT DISTINCT(event_id) FROM EventReservations WHERE reservationDate BETWEEN '" + startDate + "' AND '" + endDate + "';");

        for (int r : result) {
            if (result == null){
                    report = "Brak dostępnych wydarzeń w okresie " + startDate + " - " + endDate;
            } else {
                int count = db.getDataInt("SELECT COUNT(*) FROM EventReservations WHERE event_id = " + r);
                String event = db.getDataString("SELECT name FROM Events WHERE event_id = " + r);
                report += event + " - " + count + " uczestników; \n";
            }
        }


        db.callProcedure("addReport", "EVENT REPORT", report, workerID);
        return db.getDataString("SELECT details FROM Reports ORDER BY report_id DESC LIMIT 1;");

    }
    public String generateExhibitionReport(DB db, String startDate, String endDate, String workerID){
        String report = "";
        String sql = "SELECT exhibition_id, name, location FROM Exhibitions WHERE start_date BETWEEN '" + startDate + "' AND '" + endDate + "';";
        ResultSet r = db.executeQuery(db.getSt(), sql);
        if (r == null){
            report = "Brak dostępnych tranzakcji w okresie " + startDate + " - " + endDate;
        } else {
            report = db.sqlGetDataByName(r);
        }

        db.callProcedure("addReport", "EXHIBITION REPORT", report, workerID);
        return db.getDataString("SELECT details FROM Reports ORDER BY report_id DESC LIMIT 1;");
    }

}

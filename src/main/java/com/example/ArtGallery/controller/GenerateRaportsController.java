package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.reports.Report;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class GenerateRaportsController implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ChoiceBox choseRaportChoiceBox;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;
    @FXML
    private Label warningLabel;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    private Report report;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choseRaportChoiceBox.getItems().add("Raport finansowy");
        choseRaportChoiceBox.getItems().add("Raport wydarzeń");
        choseRaportChoiceBox.getItems().add("Raport z wystaw");
        choseRaportChoiceBox.setValue("Raport finansowy");

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String r = (String) choseRaportChoiceBox.getValue();
                report = new Report();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate startDate = fromDatePicker.getValue();
                LocalDate endDate = toDatePicker.getValue();

                String formattedStartDate = startDate.format(formatter);
                String formattedEndDate = endDate.format(formatter);

                String reportsDetails = "NULL";

                switch(r){
                    case "Raport finansowy":
                        reportsDetails = report.generateFinancialReport(db, formattedStartDate, formattedEndDate, user.getID());
                        break;
                    case "Raport wydarzeń":
                        reportsDetails = report.generateEventReport(db, formattedStartDate, formattedEndDate, user.getID());
                        break;
                    case "Raport z wystaw":
                        reportsDetails = report.generateExhibitionReport(db, formattedStartDate, formattedEndDate, user.getID());
                }

                warningLabel.setText("Report generated!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("reportName: " + r);
                System.out.println("reportDetails: " + reportsDetails);
                System.out.println("------------------------------------------------");
                sc.changeSceneReport(event, "/com/example/ArtGallery/ReportDetails.fxml", "Report details", user, r, reportsDetails);

            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String type = "LOG";
                if(user != null){
                    type = db.getDataString("SELECT user_type FROM users WHERE user_id = \"" + user.getID() + "\";");
                }

                switch (type){
                    case "ADM":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/AdminWindow.fxml", "Art Haven - Admin", user);
                        break;
                    case "CLI":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
                        break;
                    case "CRT":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
                        break;
                    case "MNG":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/ManagerWindow.fxml", "Art Haven - Manager", user);
                        break;
                    case "MRT":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/MarketingWindow.fxml", "Art Haven - Marketing", user);
                        break;
                    default:
                        sc.changeSceneUser(event, "/com/example/ArtGallery/hello-view.fxml", "Art Haven", user);
                        break;
                }
            }
        });
    }
}

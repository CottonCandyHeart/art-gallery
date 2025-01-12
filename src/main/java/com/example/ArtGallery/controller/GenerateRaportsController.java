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
        choseRaportChoiceBox.getItems().add("Raport kolekcji");
        choseRaportChoiceBox.setValue("Raport finansowy");

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String r = (String) choseRaportChoiceBox.getValue();
                report = new Report();
                switch(r){
                    case "Raport finansowy":
                        report.generateFinancialReport(db, "", "", "");
                        break;
                    case "Raport wydarzeń":
                        report.generateEventReport(db, "", "", "");
                        break;
                    case "Raport kolekcji":
                        report.generateCollectionReport(db, "", "", "");
                }


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

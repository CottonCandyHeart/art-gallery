package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
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

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //obsługa usunięcia usera
                //jeżeli admin
                sc.changeSceneUser(event, "/com/example/ArtGallery/AdminWindow.fxml", "Art Haven - Admin", user);
                //jeżeli client
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
                //jeżeli kurator
                //sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
                //jeżeli manager
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ManagerWindow.fxml", "Art Haven - Manager", user);
                //jeżeli marketingowca
                //sc.changeSceneUser(event, "/com/example/ArtGallery/MarketingWindow.fxml", "Art Haven - Marketing", user);
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //jeżeli admin
                sc.changeSceneUser(event, "/com/example/ArtGallery/AdminWindow.fxml", "Art Haven - Admin", user);
                //jeżeli client
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
                //jeżeli kurator
                //sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
                //jeżeli manager
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ManagerWindow.fxml", "Art Haven - Manager", user);
                //jeżeli marketingowca
                //sc.changeSceneUser(event, "/com/example/ArtGallery/MarketingWindow.fxml", "Art Haven - Marketing", user);
            }
        });
    }
}

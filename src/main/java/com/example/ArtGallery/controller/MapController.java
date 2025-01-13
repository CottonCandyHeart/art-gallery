package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Button sala1Button;
    @FXML
    private Button sala2Button;
    @FXML
    private Button sala3Button;
    @FXML
    private Button sala4Button;
    @FXML
    private Button sala5Button;
    @FXML
    private Button sala6Button;
    @FXML
    private Button sala7Button;
    @FXML
    private Button sala8Button;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        sala1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 1);
            }
        });
        sala2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 2);
            }
        });
        sala3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 3);
            }
        });
        sala4Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 4);
            }
        });
        sala5Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 5);
            }
        });
        sala6Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 6);
            }
        });
        sala7Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 7);
            }
        });
        sala8Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, 8);
            }
        });
    }
}
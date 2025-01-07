package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class MarketingWindowController implements Initializable {
    @FXML
    private Button managePromoButton;
    @FXML
    private Button collectionButton5;
    @FXML
    private Button artistsButton5;
    @FXML
    private Button eventsButton5;
    @FXML
    private Button mapButton5;
    @FXML
    private Button generateRaportsButton;
    @FXML
    private Button LogOutButton4;

    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        managePromoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage Users - Marketing", user);
            }
        });

        collectionButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Collection.fxml", "Collection - Marketing", user);
            }
        });

        artistsButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Artists.fxml", "Artists - Marketing", user);
            }
        });

        eventsButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Events.fxml", "Events - Marketing ", user);
            }
        });

        mapButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map - Marketing", user);
            }
        });
        generateRaportsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/GenerateRaports.fxml", "Generate Raports - Marketing", user);
            }
        });
        LogOutButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!" );
            }
        });


    }
}
package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerWindowController implements Initializable {
    @FXML
    private Button manageEventsButton;
    @FXML
    private Button collectionButton4;
    @FXML
    private Button artistsButton4;
    @FXML
    private Button eventsButton4;
    @FXML
    private Button mapButton4;
    @FXML
    private Button generateRaportsButton;
    @FXML
    private Button LogOutButton3;

    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manageEventsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage Users - Manager", user);
            }
        });

        collectionButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Collection.fxml", "Collection - Manager", user);
            }
        });

        artistsButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Artists.fxml", "Artists - Manager", user);
            }
        });

        eventsButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Events.fxml", "Events - Manager ", user);
            }
        });

        mapButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map - Manager", user);
            }
        });

        generateRaportsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/GenerateRaports.fxml", "Generate Raports - Manager", user);
            }
        });
        LogOutButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!" );
            }
        });


    }
}
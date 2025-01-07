package com.example.ArtGallery.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.ArtGallery.model.users.User;

public class AdminWindowController implements Initializable
{
    @FXML
    private Button manageUsersButton;
    @FXML
    private Button collectionButton2;
    @FXML
    private Button artistsButton2;
    @FXML
    private Button eventsButton2;
    @FXML
    private Button mapButton2;
    @FXML
    private Button generateRaportsButton;
    @FXML
    private Button LogOutButton1;

    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manageUsersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage Users - Admin", user);
            }
        });

        collectionButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Collection.fxml", "Collection - Admin", user);
            }
        });

        artistsButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Artists.fxml", "Artists - Admin", user);
            }
        });

        eventsButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Events.fxml", "Events - Admin", user);
            }
        });

        mapButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map - Admin", user);
            }
        });
        generateRaportsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/GenerateRaports.fxml", "Generate Raports - Admin", user);
            }
        });
        LogOutButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!" );
            }
        });


    }
}
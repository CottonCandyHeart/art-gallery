package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class CuratorWindowController implements Initializable {
    @FXML
    private Button manageArtworksButton;
    @FXML
    private Button collectionButton3;
    @FXML
    private Button artistsButton3;
    @FXML
    private Button eventsButton3;
    @FXML
    private Button mapButton3;
    @FXML
    private Button LogOutButton2;

    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manageArtworksButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage Users - Art Curator", user);
            }
        });

        collectionButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Collection.fxml", "Collection - Art Curator", user);
            }
        });

        artistsButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Artists.fxml", "Artists - Art Curator", user);
            }
        });

        eventsButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Events.fxml", "Events - Art Curator", user);
            }
        });

        mapButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map - Art Curator", user);
            }
        });
        LogOutButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!" );
            }
        });


    }
}
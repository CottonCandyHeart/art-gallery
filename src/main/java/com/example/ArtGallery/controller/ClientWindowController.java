package com.example.ArtGallery.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.ArtGallery.model.users.User;

public class ClientWindowController implements Initializable
{
    @FXML
    private Button myAccountButton;
    @FXML
    private Button myArtworkButton;
    @FXML
    private Button collectionButton1;
    @FXML
    private Button artistsButton1;
    @FXML
    private Button eventsButton1;
    @FXML
    private Button mapButton1;

    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
        //przypisanie usera po zalogowaniu/rejestracji
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user != null)
            System.out.println("ClientWindow - " + user.getID());
        else
            System.out.println("ClientWindow - no user");
        myAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/MyAccount.fxml", "My Account", user);
            }
        });

        myArtworkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
            }
        });

        collectionButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Collection.fxml", "Collection", user);
            }
        });

        artistsButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Artists.fxml", "Artists", user);
            }
        });

        eventsButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Events.fxml", "Events", user);
            }
        });

        mapButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map", user);
            }
        });

    }
}
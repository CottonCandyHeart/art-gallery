package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;


import java.net.URL;
import java.util.ResourceBundle;

public class ChooseArtworkController implements Initializable {
    @FXML
    private Button cancelButton11;
    @FXML
    private Button addArtworkButton;

    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        System.out.println("setUser - " + user.getID());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancelButton11.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
            }
        });

        addArtworkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/AddArtwork.fxml", "Add Artwork - Art Curator", user);
            }
        });
    }

}


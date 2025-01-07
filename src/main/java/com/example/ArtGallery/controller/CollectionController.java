package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    @FXML
    private Button cancelButton8;

    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        System.out.println("setUser - " + user.getID());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancelButton8.setOnAction(new EventHandler<ActionEvent>() {
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


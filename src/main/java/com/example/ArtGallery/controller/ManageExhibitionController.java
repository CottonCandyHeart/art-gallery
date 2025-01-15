package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageExhibitionController implements Initializable {
    @FXML
    private Button editExhibitionButton;
    @FXML
    private Button addExhibitionButton;
    @FXML
    private Button deleteExhibitionButton;
    @FXML
    private Button cancelButton;

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
                sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Curator", user);
            }
        });
        editExhibitionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/EditExhibition.fxml", "Edit exhibition - Curator", user);
            }
        });
        addExhibitionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/AddExhibition.fxml", "Edit Event- Curator", user);
            }
        });
        deleteExhibitionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/DeleteExhibition.fxml", "Delete Exhibition- Curator", user);
            }
        });

    }
}
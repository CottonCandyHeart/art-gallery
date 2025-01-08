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

public class DeleteUserController implements Initializable {
    @FXML
    private Button confirmButton3;
    @FXML
    private Button cancelButton7;
    @FXML
    private ChoiceBox choseUserChoiceBox1;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //obsługa usuwania usera
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage users - Admin", user);
            }
        });
        cancelButton7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage users - Admin", user);
            }
        });
    }
}

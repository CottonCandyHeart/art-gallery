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

public class DeleteEventController implements Initializable {
    @FXML
    private Button confirmButton6;
    @FXML
    private Button cancelButton15;
    @FXML
    private Label warningLabel8;
    @FXML
    private ChoiceBox chooseEventChoiceBox1;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //obsługa usunięcia eventu
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
            }
        });
        cancelButton15.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
            }
        });
    }
}

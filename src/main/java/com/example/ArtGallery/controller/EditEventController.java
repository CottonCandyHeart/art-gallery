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

public class    EditEventController implements Initializable {
    @FXML
    private Button confirmButton5;
    @FXML
    private Button cancelButton14;
    @FXML
    private Label warningLabel7;
    @FXML
    private ChoiceBox chooseEventChoiceBox;
    @FXML
    private TextField eventNameTextField1;
    @FXML
    private TextField eventDateTextField1;
    @FXML
    private TextField exhibitionTextField1;
    @FXML
    private TextField typeTextField1;
    @FXML
    private TextField capacityTextField1;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //obsługa edycji eventu
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
            }
        });
        cancelButton14.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
            }
        });
    }
}

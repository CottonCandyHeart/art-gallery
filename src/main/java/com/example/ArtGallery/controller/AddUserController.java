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

public class AddUserController implements Initializable {
    @FXML
    private Button confirmButton2;
    @FXML
    private Button cancelButton6;
    @FXML
    private ChoiceBox choseUsersTypeChoiceBox;
    @FXML
    private Label warningLabel4;
    @FXML
    private TextField nameTextField3;
    @FXML
    private TextField surnameTextField3;
    @FXML
    private TextField phoneTextField3;
    @FXML
    private PasswordField usernameField2;
    @FXML
    private PasswordField passwordField4;
    @FXML
    private PasswordField confirmPasswordField3;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //obsługa dodania usera
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage users - Admin", user);
            }
        });
        cancelButton6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage users - Admin", user);
            }
        });
    }
}

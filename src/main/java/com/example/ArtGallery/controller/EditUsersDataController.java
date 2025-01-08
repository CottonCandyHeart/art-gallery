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

public class EditUsersDataController implements Initializable {
    @FXML
    private Button confirmButton1;
    @FXML
    private Button cancelButton5;
    @FXML
    private ChoiceBox choseUserChoiceBox;
    @FXML
    private TextField userTextField;

    @FXML
    private Label warningLabel3;
    @FXML
    private TextField nameTextField2;
    @FXML
    private TextField surnameTextField2;
    @FXML
    private TextField phoneTextField2;
    @FXML
    private PasswordField passwordField3;
    @FXML
    private PasswordField confirmPasswordField2;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //obsługa zmiany danych użytkownika
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage users - Admin", user);
            }
        });
        cancelButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageUsers.fxml", "Manage users - Admin", user);
            }
        });
    }
}

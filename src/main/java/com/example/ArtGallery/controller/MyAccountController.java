package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.users.Client;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MyAccountController  implements Initializable {

    @FXML
    private Label usernameText;
    @FXML
    private Label nameText;
    @FXML
    private Label surnameText;
    @FXML
    private Label phoneText;
    @FXML
    private Button cancelButton3;
    @FXML
    private Button editButton;

    private SceneController sc = new SceneController();
    User user;
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usernameText.setText(user.getUsername());
                nameText.setText(user.getName());
                surnameText.setText(user.getSurname());
                phoneText.setText("" + ((Client) user).getPhoneNo());

                sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
            }
        });
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/UserEdit.fxml", "Edit!", user);
            }
        });
    }
}

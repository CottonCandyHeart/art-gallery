package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserEditController implements Initializable {
    @FXML
    private Button cancelButton4;
    @FXML
    private Button confirmButton;
    @FXML
    private Label warningLabel2;
    @FXML
    private TextField nameTextField1;
    @FXML
    private TextField surnameTextField1;
    @FXML
    private TextField phoneTextField1;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private PasswordField confirmPasswordField1;

    private DB db = new DB();
    private SceneController sc = new SceneController();

    private User user;
    public void setUser(User user) {
        this.user = user;
        //przypisanie usera po zalogowaniu/rejestracji
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String name = nameTextField1.getText();
                String surname = surnameTextField1.getText();
                String phoneNo = phoneTextField1.getText();
                String password = passwordField2.getText();

                if (name != null) {user.setName(name);}
                if (surname != null) {user.setSurname(surname);}
                if (phoneNo != null) { ((Client) user).setPhoneNo(Integer.parseInt(phoneNo));}

                if (password != null) {
                    String checkpasswd = confirmPasswordField1.getText();
                    if (password.equals(checkpasswd)){
                        user.modPasswd(db, password);
                    }
                }

                user.modUser(db);


            }
        });
        cancelButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
            }
        });
    }
}

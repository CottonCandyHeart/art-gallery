package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import com.example.ArtGallery.model.users.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
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
    private TextField usernameTextField2;
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
        List<String> types = db.getDataStringList("SELECT role FROM roles;");
        choseUsersTypeChoiceBox.getItems().addAll(types);
        choseUsersTypeChoiceBox.setValue(types.get(0));

        confirmButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameTextField3.getText();
                String surname = surnameTextField3.getText();
                int phoneNo = Integer.parseInt(phoneTextField3.getText());
                String login = usernameTextField2.getText();
                String loginExists = db.getDataString("SELECT username FROM Users WHERE username LIKE \"" + login + "\";");
                String password = passwordField4.getText();
                String confirmPassword = confirmPasswordField3.getText();
                String role = (String) choseUsersTypeChoiceBox.getValue();
                String role_type = null;
                switch (role) {
                    case "Administrator":
                        role_type = "ADM";
                        break;
                    case "Kurator Sztuki":
                        role_type = "CRT";
                        break;
                    case "Manager":
                        role_type = "Mng";
                        break;
                    case "Marketingowiec":
                        role_type = "MRT";
                        break;
                }
                if (loginExists != null) {
                    warningLabel4.setText("User already exists!");
                } else if (password.equals(confirmPassword)) {
                    Integer countRole = db.getDataInt("SELECT COUNT(*) FROM users WHERE user_type LIKE \"" + role_type + "\";");
                    String ID = role_type + countRole.toString();
                    User user = new Worker(ID, login, name, surname,role);
                    user.addUser(db, password, role_type);
                    warningLabel4.setText("Worker created!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    db.closeConnection(db.getCon(), db.getSt());
                    //sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);


                } else {
                    warningLabel4.setText("Passwords do not match!");
                }
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

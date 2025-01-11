package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.Client;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditUsersDataController implements Initializable {
    @FXML
    private Button confirmButton1;
    @FXML
    private Button cancelButton5;
    @FXML
    private ChoiceBox choseUserTypeChoiceBox;
    @FXML
    private ChoiceBox choseUsernameChoiceBox;

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
        List<String> types = db.getDataStringList("SELECT role FROM Roles;");
        choseUserTypeChoiceBox.getItems().addAll(types);
        choseUserTypeChoiceBox.setValue(types.get(0));

        List<String> users = db.getDataStringList("SELECT username FROM Users JOIN Roles ON Users.user_type = Roles.role_id WHERE Roles.role = \"" + types.get(0) + "\"");
        choseUsernameChoiceBox.getItems().addAll(users);
        choseUsernameChoiceBox.setValue(users.get(0));


        choseUserTypeChoiceBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String role = (String) choseUserTypeChoiceBox.getValue();

                List<String> users = db.getDataStringList("SELECT username FROM Users JOIN Roles ON Users.user_type = Roles.role_id WHERE Roles.role = \"" + role + "\"");

                if (users == null || users.isEmpty()) {
                    return;
                }

                choseUsernameChoiceBox.getItems().clear();
                choseUsernameChoiceBox.getItems().addAll(users);
                choseUsernameChoiceBox.setValue(users.get(0));
            }
        });
        confirmButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameTextField2.getText();
                String surname = surnameTextField2.getText();
                String phoneNo = phoneTextField2.getText();
                String password = passwordField3.getText();

                String role = (String) choseUserTypeChoiceBox.getValue();
                String username = (String) choseUsernameChoiceBox.getValue();
                String role_type = "";

                switch (role) {
                    case "Administrator":
                        role_type = "ADM";
                        break;
                    case "Kurator Sztuki":
                        role_type = "CRT";
                        break;
                    case "Manager":
                        role_type = "MNG";
                        break;
                    case "Marketingowiec":
                        role_type = "MRT";
                        break;
                    case "Klient":
                        role_type = "CLI";
                }

                String ID = db.getDataString("SELECT user_id FROM Users WHERE username = \"" + username + "\" AND user_type = \"" + role_type + "\";");
                System.out.println("ID: " + ID);
                User modUser = new User(ID,"NULL","NULL", "NULL");

                if (!nameTextField2.getText().isEmpty()) {
                    db.executeUpdate(db.getSt(), "UPDATE Users SET name = \"" + name + "\" WHERE username = \"" + username + "\" AND user_type = \"" + role_type + "\";");
                }

                if (!surnameTextField2.getText().isEmpty()) {
                    db.executeUpdate(db.getSt(), "UPDATE Users SET surname = \"" + surname + "\" WHERE username = \"" + username + "\" AND user_type = \"" + role_type + "\";");
                }

                if (!phoneTextField2.getText().isEmpty()) {
                    db.executeUpdate(db.getSt(), "UPDATE Users SET phoneNo = \"" + phoneNo + "\" WHERE username = \"" + username + "\" AND user_type = \"" + role_type + "\";");
                }

                if (!passwordField3.getText().isEmpty()) {
                    String checkpasswd = confirmPasswordField2.getText();
                    if (password.equals(checkpasswd)){
                        modUser.modPasswd(db, password);
                    }
                }

                warningLabel3.setText("User modified!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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

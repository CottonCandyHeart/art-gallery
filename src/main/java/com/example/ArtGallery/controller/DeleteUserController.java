package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteUserController implements Initializable {
    @FXML
    private Button confirmButton3;
    @FXML
    private Button cancelButton7;
    @FXML
    private ChoiceBox chooseUserTypeChoiceBox;
    @FXML
    private ChoiceBox chooseUsernameChoiceBox;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> types = db.getDataStringList("SELECT role FROM Roles;");
        chooseUserTypeChoiceBox.getItems().addAll(types);
        chooseUserTypeChoiceBox.setValue(types.get(0));

        List<String> users = db.getDataStringList("SELECT username FROM Users JOIN Roles ON Users.user_type = Roles.role_id WHERE Roles.role = \"" + types.get(0) + "\"");
        chooseUsernameChoiceBox.getItems().addAll(users);
        chooseUsernameChoiceBox.setValue(users.get(0));


        chooseUserTypeChoiceBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String role = (String) chooseUserTypeChoiceBox.getValue();

                List<String> users = db.getDataStringList("SELECT username FROM Users JOIN Roles ON Users.user_type = Roles.role_id WHERE Roles.role = \"" + role + "\"");

                if (users == null || users.isEmpty()) {
                    return;
                }

                chooseUsernameChoiceBox.getItems().clear();
                chooseUsernameChoiceBox.getItems().addAll(users);
                chooseUsernameChoiceBox.setValue(users.get(0));
            }
        });


        confirmButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String role = (String) chooseUserTypeChoiceBox.getValue();
                String username = (String) chooseUsernameChoiceBox.getValue();
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

                db.executeUpdate(db.getSt(), "DELETE FROM Users WHERE username = \"" + username + "\" AND user_type = \"" + role_type + "\";");
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

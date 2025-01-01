package com.example.ArtGallery.controller;

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
import org.mindrot.jbcrypt.BCrypt;

import com.example.ArtGallery.db.*;
import com.example.ArtGallery.model.users.*;

public class LogInController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Button logInButton1;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                    warningLabel.setText("Please fill all fields!");
                    return;
                }

                String username = db.getDataString("SELECT username FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");

                if (username == null) {
                    warningLabel.setText("User does not exist!");
                }else{
                    String hashedPassword = db.getDataString("SELECT password FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");
                    if(BCrypt.checkpw(passwordField.getText(), hashedPassword)) {
                        warningLabel.setText("Logged in!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        String ID = db.getDataString("SELECT user_id FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");
                        String userType = db.getDataString("SELECT user_type FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");
                        String name = db.getDataString("SELECT name FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");
                        String surname = db.getDataString("SELECT surname FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");

                        if (userType.equals("CLI")){
                            int phoneNo = db.getDataInt("SELECT phoneNo FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");
                            user = new Client(ID, username, name, surname, phoneNo);
                        } else {
                            String role = db.getDataString("SELECT role FROM Users WHERE username LIKE \"" + usernameTextField.getText() + "\";");
                            user = new Worker(ID, username, name, surname, role);
                        }

                        sc.changeSceneUser(event, "MainWindowGUI.fxml", "Art Haven", user);
                        db.closeConnection(db.getCon(), db.getSt());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


                    }else{
                        warningLabel.setText("Wrong password!");
                    }
                }

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!");
            }
        });
    }
}

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

public class SignUpController implements Initializable {
    @FXML
    private Button cancelButton1;
    @FXML
    private Button signUpButton1;
    @FXML
    private Label warningLabel1;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField usernameTextField1;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private PasswordField confirmPasswordField;

    //private DataBase db = new DataBase(); //tak było jak robiliśmy warcaby

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpButton1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //DataBase db = new DataBase();

                if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || ageTextField.getText().isEmpty() ||usernameTextField1.getText().isEmpty() || passwordField1.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                    warningLabel1.setText("Please fill all fields!");
                    return;
                }

                /*
                String login = db.getDataString("SELECT login FROM user where login like \"" + usernameTextField.getText() + "\";");
                String password = passwordTextField.getText();
                String confirmPassword = confirmPasswordTextField.getText();
                if (login != null) {
                    warningLabel.setText("User already exists!");
                } else if (password.equals(confirmPassword)) {
                    db.executeUpdate(db.getSt(), "INSERT INTO user (login, haslo, ilosc_wygranych, ilosc_przegranych, ilosc_remisow) VALUES (\"" + usernameTextField.getText() + "\", \"" + passwordTextField.getText() + "\", 0, 0, 0);");
                    warningLabel.setText("User created!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    User user = new User(usernameTextField.getText(), 0, 0, 0);
                    db.closeConnection(db.getCon(), db.getSt());
                    //xzczx
                    DBUtils.changeSceneUser(event, "MainWindowGUI.fxml", "Game Window!", user);
                } else {
                    warningLabel.setText("Passwords do not match!");
                }*/
            }
        });
        cancelButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!");
            }
        });
    }
}

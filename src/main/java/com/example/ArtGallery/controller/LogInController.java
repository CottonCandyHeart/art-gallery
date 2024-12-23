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

    //private DataBase db = new DataBase(); //tak było jak robiliśmy warcaby

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                    warningLabel.setText("Please fill all fields!");
                    return;
                }
                //obsługa logowania z warcabów:
                /*
                String login = db.getDataString("SELECT login FROM user where login like \"" + usernameTextField.getText() + "\";");

                if (login == null) {
                    warningLabel.setText("User does not exist!");
                }else{
                    String password = db.getDataString("SELECT haslo FROM user where login like \"" + usernameTextField.getText() + "\";");
                    if(password.equals(passwordTextField.getText())){
                        warningLabel.setText("Logged in!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Integer wins = db.getDataInt("SELECT ilosc_wygranych FROM user where login like \"" + usernameTextField.getText() + "\";");
                        Integer losses = db.getDataInt("SELECT ilosc_przegranych FROM user where login like \"" + usernameTextField.getText() + "\";");
                        Integer draws = db.getDataInt("SELECT ilosc_remisow FROM user where login like \"" + usernameTextField.getText() + "\";");
                        User user = new User(usernameTextField.getText(), wins,draws,losses);
                        DBUtils.changeSceneUser(event, "MainWindowGUI.fxml", "Game Window!", user);
                        db.closeConnection(db.getCon(), db.getSt());
                        //DBUtils.changeScene(event, "MainWindowGUI.fxml", "Main Window!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        warningLabel.setText("Wrong password!");
                    }
                }*/
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

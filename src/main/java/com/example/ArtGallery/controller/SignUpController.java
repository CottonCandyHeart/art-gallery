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

    private DB db = new DB();
    private SceneController sc = new SceneController();

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

                String login = usernameTextField1.getText();
                String loginExists = db.getDataString("SELECT username FROM Users WHERE username LIKE \"" + login + "\";");
                String password = passwordField1.getText();  // TODO Szyfrowanie
                String confirmPassword = confirmPasswordField.getText();
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                int age = Integer.parseInt(ageTextField.getText()) ;
                int phoneNo = 123456789;
                // TODO numer telefonu dodać


                if (loginExists != null) {
                    warningLabel1.setText("User already exists!");
                } else if (password.equals(confirmPassword)) {
                    if (age > 18){
                        db.executeUpdate(db.getSt(), "CALL 'addUser'('CLI','" + login + "','" + password + "','" + name + "','" + surname + "'," + phoneNo + ",'NULL';");
                        warningLabel1.setText("User created!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        String id = db.getDataString("SELECT user_id FROM Users WHERE username LIKE \"" + login + "\";");
                        User user = new Client(id, login, name, surname, phoneNo);
                        db.closeConnection(db.getCon(), db.getSt());
                        sc.changeSceneUser(event, "MainWindowGUI.fxml", "Art Haven", user);
                    }

                } else {
                    warningLabel1.setText("Passwords do not match!");
                }

            }
        });
        cancelButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!" );
            }
        });
    }
}

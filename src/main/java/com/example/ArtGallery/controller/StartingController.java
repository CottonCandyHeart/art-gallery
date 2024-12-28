package com.example.ArtGallery.controller;

import com.example.ArtGallery.artists.Artist;
import com.example.ArtGallery.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

@Controller
public class StartingController implements Initializable
{
    @FXML
    private Button signUpButton;
    @FXML
    private Button logInButton;
    @FXML
    private Button collectionButton;
    @FXML
    private Button artistsButton;
    @FXML
    private Button eventsButton;
    @FXML
    private Button mapButton;

    //private User user;

    /*public void setUser(User user) {
        this.user = user;
        usernameLabel.setText(user.getUsername());
        winsScoreLabel.setText(String.valueOf(user.getWins()));
        defeatsScoreLabel.setText(String.valueOf(user.getLost()));
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/LogIn.fxml", "Log In!");
            }
        });

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/SignUp.fxml", "Sign Up!");
            }
        });

        collectionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/Collection.fxml", "Collection!");
            }
        });

        artistsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/Artists.fxml", "Artists!");
            }
        });

        eventsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/Events.fxml", "Events!");
            }
        });

        mapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/Map.fxml", "Gallery's map!");
            }
        });

    }
}
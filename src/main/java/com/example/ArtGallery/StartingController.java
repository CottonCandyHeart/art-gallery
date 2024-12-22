package com.example.ArtGallery;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartingController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
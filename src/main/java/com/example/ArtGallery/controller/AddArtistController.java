package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.exhibitions.Exhibition;
import com.example.ArtGallery.model.users.User;
import com.example.ArtGallery.model.users.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AddArtistController implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField bioTextField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private DatePicker deathDatePicker;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || birthDatePicker.getValue() == null){
                    warningLabel.setText("Fill all fields");
                }else {
                    String name = nameTextField.getText();
                    String surname = surnameTextField.getText();
                    String bio = bioTextField.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate birthDate = birthDatePicker.getValue();
                    String formattedStartDate = birthDate.format(formatter);
                    LocalDate deathDate = deathDatePicker.getValue();
                    String formattedEndDate = deathDate.format(formatter);
                    if(deathDate == null) {
                        Artist artist = new Artist(-1, name, surname, bio, formattedStartDate, null);
                        artist.addArtist(db);
                        sc.changeSceneUser(event, "/com/example/ArtGallery/ManageArtists.fxml", "Manage artists - Art Curator", user);
                    }else if (birthDate.isAfter(deathDate)) {
                        warningLabel.setText("Start date must be before end date");
                    } else {
                        Artist artist = new Artist(-1, name, surname, bio, formattedStartDate, formattedEndDate);
                        artist.addArtist(db);
                        sc.changeSceneUser(event, "/com/example/ArtGallery/ManageArtists.fxml", "Manage artists - Art Curator", user);
                    }

                }
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageArtists.fxml", "Manage artists - Art Curator", user);
            }
        });
    }
}
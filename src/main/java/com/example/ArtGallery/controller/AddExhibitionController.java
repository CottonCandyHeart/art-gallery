package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.exhibitions.Exhibition;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddExhibitionController implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private ChoiceBox locationChoiceBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> location = Arrays.asList("Sala 1", "Sala 2", "Sala 3", "Sala 4", "Sala 5", "Sala 6", "Salaa 7", "Sala 8");
        locationChoiceBox.getItems().addAll(location);
        locationChoiceBox.setValue(location.get(0));
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue() == null){
                    warningLabel.setText("Fill all fields");
                }else {
                    String name = nameTextField.getText();
                    String description = descriptionTextField.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateStart = startDatePicker.getValue();
                    String formattedStartDate = dateStart.format(formatter);
                    LocalDate dateEnd = endDatePicker.getValue();
                    String formattedEndDate = dateEnd.format(formatter);
                    String location = locationChoiceBox.getValue().toString();
                    if (dateStart.isAfter(dateEnd)) {
                        warningLabel.setText("Start date must be before end date");
                    } else {
                        Exhibition exhibition = new Exhibition(-1, name, formattedStartDate, formattedEndDate, location, description);
                        exhibition.addExhibition(db);
                    }

                }
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageExhibition.fxml", "Manage exhibition - Art Curator", user);

            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageExhibition.fxml", "Manage exhibition - Art Curator", user);
            }
        });
    }
}
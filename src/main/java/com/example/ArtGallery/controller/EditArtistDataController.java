package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
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

public class EditArtistDataController implements Initializable {

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label warningLabel;
    @FXML
    private ChoiceBox artistChoiceBox;
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
        List<String> artists = db.getDataStringList("SELECT CONCAT(name, ' ' , surname) FROM artists;");
        if(artists.isEmpty()) {
            warningLabel.setText("No artists to edit");
            confirmButton.setDisable(true);
        }else{
            artistChoiceBox.getItems().addAll(artists);
            artistChoiceBox.setValue(artists.get(0));
        }
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String artist = artistChoiceBox.getValue().toString();
                String name = artist.substring(0,artist.indexOf(" "));
                String surname = artist.substring(artist.indexOf(" ")+1);
                Integer id = db.getDataInt("SELECT artist_id FROM artists WHERE name LIKE '" + name + "' AND surname LIKE '"+ surname +"';");
                if(!nameTextField.getText().isEmpty()){
                    db.executeUpdate(db.getSt(),"UPDATE artists SET name = '" + nameTextField.getText() + "' WHERE artist_id = " + id + ";");
                }
                if(!surnameTextField.getText().isEmpty()){
                    db.executeUpdate(db.getSt(),"UPDATE artists SET surname = '" + surnameTextField.getText() + "' WHERE artist_id = " + id + ";");
                }
                if(!bioTextField.getText().isEmpty()){
                    db.executeUpdate(db.getSt(),"UPDATE artists SET bio = '" + bioTextField.getText() + "' WHERE artist_id = " + id + ";");
                }
                if(birthDatePicker.getValue() != null){
                    LocalDate birthDate = birthDatePicker.getValue();
                    String formattedBirthDate = birthDate.format(formatter);

                    LocalDate checkDeathDate = db.getDataDate("SELECT death_date FROM artists WHERE artist_id = " + id + ";").toLocalDate();
                    System.out.println(checkDeathDate);
                    if(checkDeathDate != null && birthDate.isAfter(checkDeathDate)){
                        warningLabel.setText("Birth date cannot be after death date");
                        return;
                    }else{
                        db.executeUpdate(db.getSt(),"UPDATE artists SET birth_date = '" + formattedBirthDate + "' WHERE artist_id = " + id + ";");

                    }
                }

                if(deathDatePicker.getValue() != null){
                    LocalDate deathDate = deathDatePicker.getValue();
                    String formattedDeathDate = deathDate.format(formatter);

                    LocalDate checkBirthDate = db.getDataDate("SELECT birth_date FROM artists WHERE artist_id = " + id + ";").toLocalDate();
                    if(checkBirthDate != null && deathDate.isBefore(checkBirthDate)){
                        warningLabel.setText("Death date cannot be before birth date");
                        return;
                    }else{
                        db.executeUpdate(db.getSt(),"UPDATE artists SET death_date = '" + formattedDeathDate + "' WHERE artist_id = " + id + ";");
                    }
                }

                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageArtists.fxml", "Manage artists - Art Curator", user);
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
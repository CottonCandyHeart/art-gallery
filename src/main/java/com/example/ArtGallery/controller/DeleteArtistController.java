package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteArtistController implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ChoiceBox artistChoiceBox;
    @FXML
    private Label warningLabel;

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
            warningLabel.setText("No artists to delete");
            confirmButton.setDisable(true);
        }else{
            artistChoiceBox.getItems().addAll(artists);
            artistChoiceBox.setValue(artists.get(0));
        }
        artistChoiceBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

            }
        });


        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String artist = artistChoiceBox.getValue().toString();
                String name = artist.substring(0,artist.indexOf(" "));
                String surname = artist.substring(artist.indexOf(" ")+1);
                Integer id = db.getDataInt("SELECT artist_id FROM artists WHERE name LIKE '" + name + "' AND surname LIKE '"+ surname +"';");
                db.executeUpdate(db.getSt(),"DELETE FROM artists WHERE artist_id = " + id + ";");
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageArtists.fxml", "Manage artist - Art Curator", user);
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageArtists.fxml", "Manage artist - Art Curato", user);
            }
        });
    }
}

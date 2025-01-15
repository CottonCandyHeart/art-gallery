package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddArtworkController implements Initializable {
    @FXML
    private Button confirmButton2;
    @FXML
    private Button cancelButton10;
    @FXML
    private Label warningLabel5;
    @FXML
    private TextField titleTextField;
    @FXML
    private ChoiceBox artistChoiceBox;
    @FXML
    private ChoiceBox ownerChoiceBox;
    @FXML
    private TextField creationDateTextField;
    @FXML
    private TextField methodTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField pathTextField;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> artistsList = db.getDataStringList("SELECT CONCAT(name, \" \", surname) FROM Artists;");
        artistChoiceBox.getItems().addAll(artistsList);
        artistChoiceBox.setValue(artistsList.get(0));

        List<String> usersList = db.getDataStringList("SELECT username FROM Users;");
        ownerChoiceBox.getItems().addAll(usersList);
        ownerChoiceBox.setValue(usersList.get(0));
        confirmButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (titleTextField.getText().isEmpty() || creationDateTextField.getText().isEmpty() || methodTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || locationTextField.getText().isEmpty() || statusTextField.getText().isEmpty() || pathTextField.getText().isEmpty()){
                    warningLabel5.setText("All fields must be filled!");
                } else{
                    String title = titleTextField.getText();
                    String artist = (String) artistChoiceBox.getValue();

                    String aName = artist.substring(0,artist.indexOf(" "));
                    String aSurname = artist.substring(artist.indexOf(" ")+1);
                    int aId = db.getDataInt("SELECT artist_id FROM Artists WHERE name =\"" + aName + "\" AND surname = \"" + aSurname + "\";");
                    String aBio = db.getDataString("SELECT bio FROM Artists WHERE artist_id = " + aId + ";");
                    String aBirthDate = db.getDataString("SELECT birth_date FROM Artists WHERE artist_id = " + aId + ";");
                    String aDeathDate = db.getDataString("SELECT death_date FROM Artists WHERE artist_id = " + aId + ";");
                    Artist a = new Artist(aId, aName, aSurname, aBio, aBirthDate, aDeathDate);

                    String owner = (String) ownerChoiceBox.getValue();
                    String ownerId = db.getDataString("SELECT user_id FROM Users WHERE username = \"" + owner + "\";");

                    String date = creationDateTextField.getText();
                    String method = methodTextField.getText();
                    String description = descriptionTextField.getText();
                    String location = locationTextField.getText();
                    String status = statusTextField.getText();
                    String path = pathTextField.getText();

                    Artwork artwork = new Artwork(-1, title, a, date, method, description, location, status, path);
                    artwork.addArtwork(db);
                    db.executeUpdate( db.getSt(), "UPDATE Artworks SET owner = \"" + ownerId + "\" WHERE artwork_id = " + artwork.getID() + ";");
                }

                sc.changeSceneUser(event, "/com/example/ArtGallery/ChooseArtwork.fxml", "Manage artwork - Art Curator", user);
            }
        });
        cancelButton10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ChooseArtwork.fxml", "Manage artwork - Art Curator", user);
            }
        });
    }
}

package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditArtworkController implements Initializable {
    @FXML
    private Button confirmButton2;
    @FXML
    private Button cancelButton10;
    @FXML
    private Label warningLabel4;
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

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    private Artwork artwork;
    private List<String> artistsList;
    private List<String> usersList;
    public void setUser(User user) {
        this.user = user;
    }
    public void setArtwork(Artwork artwork){
        this.artwork = artwork;

        artistsList = db.getDataStringList("SELECT CONCAT(name, \" \", surname) FROM Artists;");
        artistChoiceBox.getItems().addAll(artistsList);
        artistChoiceBox.setValue(artwork.getArtist().getName() + " " + artwork.getArtist().getSurname());

        usersList = db.getDataStringList("SELECT username FROM Users;");
        ownerChoiceBox.getItems().addAll(usersList);
        String userId = db.getDataString("SELECT owner FROM Artworks WHERE artwork_id = " + artwork.getID() + ";");
        ownerChoiceBox.setValue(db.getDataString("SELECT username FROM Users WHERE user_id = \"" + userId + "\";"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String title = titleTextField.getText();
                String artist = (String) artistChoiceBox.getValue();
                String owner = (String) ownerChoiceBox.getValue();
                String date = creationDateTextField.getText();
                String method = methodTextField.getText();
                String description = descriptionTextField.getText();
                String location = locationTextField.getText();
                String status = statusTextField.getText();

                if (!titleTextField.getText().isEmpty()){
                    artwork.setTitle(title);
                    db.executeUpdate(db.getSt(), "UPDATE Artworks SET title = \"" + title + "\" WHERE artwork_id = " + artwork.getID());
                }

                String artistName = artist.substring(0,artist.indexOf(" "));
                String artistSurname = artist.substring(artist.indexOf(" ")+1);
                int artistId = db.getDataInt("SELECT artist_id FROM Artists WHERE name = \"" + artistName + "\" AND surname = \"" + artistSurname + "\";");
                String ownerId = db.getDataString("SELECT user_id FROM Users WHERE username = \"" + owner + "\";");;
                db.executeUpdate(db.getSt(), "UPDATE Artworks SET artist_id = " + artistId + ", owner = \"" + ownerId + "\" WHERE artwork_id = " + artwork.getID() + ";");

                if (!creationDateTextField.getText().isEmpty()){
                    artwork.setCreationDate(date);
                    db.executeUpdate(db.getSt(), "UPDATE Artworks SET creation_date = \"" + date + "\" WHERE artwork_id = " + artwork.getID());
                }
                if (!methodTextField.getText().isEmpty()){
                    artwork.setMethod(method);
                    db.executeUpdate(db.getSt(), "UPDATE Artworks SET method = \"" + method + "\" WHERE artwork_id = " + artwork.getID());
                }
                if (!descriptionTextField.getText().isEmpty()){
                    artwork.setDescription(description);
                    db.executeUpdate(db.getSt(), "UPDATE Artworks SET description = \"" + description + "\" WHERE artwork_id = " + artwork.getID());
                }
                if (!locationTextField.getText().isEmpty()){
                    artwork.setLocation(location);
                    db.executeUpdate(db.getSt(), "UPDATE Artworks SET location = \"" + location + "\" WHERE artwork_id = " + artwork.getID());
                }
                if (!statusTextField.getText().isEmpty()){
                    artwork.setStatus(status);
                    db.executeUpdate(db.getSt(), "UPDATE Artworks SET status = \"" + status + "\" WHERE artwork_id = " + artwork.getID());
                }


                sc.changeSceneWithUserAndArtwork(event, "/com/example/ArtGallery/ManageArtworkAction.fxml", "Manage artwork - Art Curator", user, artwork);
            }
        });
        cancelButton10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneWithUserAndArtwork(event, "/com/example/ArtGallery/ManageArtworkAction.fxml", "Manage artwork - Art Curator", user, artwork);
            }
        });
    }

}

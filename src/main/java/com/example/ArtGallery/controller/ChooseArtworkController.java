package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseArtworkController implements Initializable {
    @FXML
    private Button cancelButton11;
    @FXML
    private Button addArtworkButton;
    @FXML
    private FlowPane artworkPanel;
    private List<Artwork> allArtwork = new ArrayList<>();
    private DB db = new DB();


    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Integer> idList = db.getDataIntList("SELECT artwork_id FROM Artworks;");
        for(int artworkId : idList){
            String title = db.getDataString("SELECT title FROM Artworks WHERE artwork_id = " + artworkId + ";");
            int artistsID = db.getDataInt("SELECT artist_id FROM Artworks WHERE artwork_id = " + artworkId + ";");
            String name = db.getDataString("SELECT name FROM Artists WHERE artist_id = " + artistsID + ";");
            String surname = db.getDataString("SELECT surname FROM Artists WHERE artist_id = " + artistsID + ";");
            String bio = db.getDataString("SELECT bio FROM Artists WHERE artist_id = " + artistsID + ";");
            String birthDate = db.getDataString("SELECT birth_date FROM Artists WHERE artist_id = " + artistsID + ";");
            String deathDate = db.getDataString("SELECT death_date FROM Artists WHERE artist_id = " + artistsID + ";");
            Artist artist = new Artist(artistsID, name, surname, bio,  birthDate, deathDate);

            String creationDate = db.getDataString("SELECT creation_date FROM Artworks WHERE artwork_id = " + artworkId + ";");
            String method= db.getDataString("SELECT method FROM Artworks WHERE artwork_id = " + artworkId + ";");
            String description = db.getDataString("SELECT description FROM Artworks WHERE artwork_id = " + artworkId + ";");
            String location = db.getDataString("SELECT location FROM Artworks WHERE artwork_id = " + artworkId + ";");
            String status = db.getDataString("SELECT status FROM Artworks WHERE artwork_id = " + artworkId + ";");
            String picturePath = "src/main/resources/artwork"+ db.getDataString("SELECT picturePath FROM Artworks WHERE artwork_id = " + artworkId + ";");
            Artwork artwork = new Artwork(artworkId,title,artist,creationDate,method,description,location,status,picturePath);

            allArtwork.add(artwork);
        }

        for (Artwork artwork : allArtwork) {

            System.out.println(artwork.getPicturePath());
            Image image = new Image("file:" + artwork.getPicturePath());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(182);
            imageView.setFitHeight(182);

            Button imageButton = new Button("", imageView);
            imageButton.setStyle("-fx-background-color: transparent;");

            imageButton.setOnAction(e -> {
                sc.changeSceneWithUserAndArtwork(e, "/com/example/ArtGallery/EditArtwork.fxml", "Edit Artwork Details", user, artwork);
            });


            artworkPanel.getChildren().add(imageButton);

        }

            cancelButton11.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
            }
        });

        addArtworkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/AddArtwork.fxml", "Add Artwork - Art Curator", user);
            }
        });
    }

}


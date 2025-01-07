package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    @FXML
    private Button cancelButton8;
    @FXML
    private FlowPane galleryPanel;

    private List<Artwork> allArtwork = new ArrayList<>();;


    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        System.out.println("setUser - " + user.getID());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 0; i<22; i++){
            int artworkId = i + 1;
            String title = db.getDataString("SELECT title FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            int artistsID = db.getDataInt("SELECT artist_id FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            String name = db.getDataString("SELECT name FROM artists WHERE artist_id = \"" + artistsID + "\";");
            String surname = db.getDataString("SELECT surname FROM artists WHERE artist_id = \"" + artistsID + "\";");
            String bio = db.getDataString("SELECT bio FROM artists WHERE artist_id = \"" + artistsID + "\";");
            String birthDate = db.getDataString("SELECT birth_date FROM artists WHERE artist_id = \"" + artistsID + "\";");
            String deathDate = db.getDataString("SELECT death_date FROM artists WHERE artist_id = \"" + artistsID + "\";");
            Artist artist = new Artist(artistsID, name, surname, bio,  birthDate, deathDate);
            String creationDate = db.getDataString("SELECT creation_date FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            String method= db.getDataString("SELECT method FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            String description = db.getDataString("SELECT description FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            String location = db.getDataString("SELECT location FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            String status = db.getDataString("SELECT status FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            String picturePath = "C:/Users/odlug/OneDrive/Desktop/semestr 5/art-gallery/src/main/resources/artwork"+ db.getDataString("SELECT picturePath FROM artworks WHERE artwork_id = \"" + artworkId + "\";");
            Artwork artwork = new Artwork(artworkId,title,artist,creationDate,method,description,location,status,picturePath);
            allArtwork.add(artwork);
        }

        for (Artwork artwork : allArtwork) {
            System.out.println(artwork.getPicturePath());
            Image image = new Image("file:"+artwork.getPicturePath());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(182);
            imageView.setFitHeight(182);

            Button imageButton = new Button("", imageView);
            imageButton.getStyleClass().add("no-border-button");
            imageButton.getStyleClass().add("button-transparent");

            imageButton.setOnAction(e -> {
                System.out.println("Wybrano obraz: " + artwork.getTitle());
                //otwieranie okna ze szczegółami i przyciskami
            });


            galleryPanel.getChildren().add(imageButton);


        }

        cancelButton8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //jeżeli admin
                sc.changeSceneUser(event, "/com/example/ArtGallery/AdminWindow.fxml", "Art Haven - Admin", user);
                //jeżeli client
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
                //jeżeli kurator
                //sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
                //jeżeli manager
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ManagerWindow.fxml", "Art Haven - Manager", user);
                //jeżeli marketingowca
                //sc.changeSceneUser(event, "/com/example/ArtGallery/MarketingWindow.fxml", "Art Haven - Marketing", user);
            }
        });
    }

}


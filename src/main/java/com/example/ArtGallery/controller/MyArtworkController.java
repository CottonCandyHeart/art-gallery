package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyArtworkController implements Initializable {
    @FXML
    private Button cancelButton8;
    @FXML
    private FlowPane galleryPanel;

    private List<Artwork> allArtwork = new ArrayList<>();;


    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
        System.out.println("USERRRRRR");
        showArtworks();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("HELLO");
        cancelButton8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
            }
        });
    }

    public void showArtworks(){
        System.out.println(user.getID());
        List<Integer> artworksId = db.getDataIntList("SELECT artwork_id FROM artworks WHERE owner = \"" + user.getID() + "\";");
        System.out.println(user.getID());

        for (int id : artworksId){
            String title = db.getDataString("SELECT title FROM Artworks WHERE artwork_id = \"" + id + "\";");
            int artistsID = db.getDataInt("SELECT artist_id FROM Artworks WHERE artwork_id = \"" + id + "\";");
            String name = db.getDataString("SELECT name FROM Artists WHERE artist_id = \"" + artistsID + "\";");
            String surname = db.getDataString("SELECT surname FROM Artists WHERE artist_id = \"" + artistsID + "\";");
            String bio = db.getDataString("SELECT bio FROM Artists WHERE artist_id = \"" + artistsID + "\";");
            String birthDate = db.getDataString("SELECT birth_date FROM Artists WHERE artist_id = \"" + artistsID + "\";");
            String deathDate = db.getDataString("SELECT death_date FROM Artists WHERE artist_id = \"" + artistsID + "\";");
            Artist artist = new Artist(artistsID, name, surname, bio,  birthDate, deathDate);

            String creationDate = db.getDataString("SELECT creation_date FROM Artworks WHERE artwork_id = \"" + id + "\";");
            String method= db.getDataString("SELECT method FROM Artworks WHERE artwork_id = \"" + id + "\";");
            String description = db.getDataString("SELECT description FROM Artworks WHERE artwork_id = \"" + id + "\";");
            String location = db.getDataString("SELECT location FROM Artworks WHERE artwork_id = \"" + id + "\";");
            String status = db.getDataString("SELECT status FROM Artworks WHERE artwork_id = \"" + id + "\";");
            String picturePath = "src/main/resources/artwork"+ db.getDataString("SELECT picturePath FROM Artworks WHERE artwork_id = \"" +id + "\";");
            Artwork artwork = new Artwork(id,title,artist,creationDate,method,description,location,status,picturePath);

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
                sc.changeSceneWithUserAndArtwork(e, "/com/example/ArtGallery/MyArtworkDetails.fxml", "Artwork - Details", user, artwork);
            });
            galleryPanel.getChildren().add(imageButton);
        }


    }

}

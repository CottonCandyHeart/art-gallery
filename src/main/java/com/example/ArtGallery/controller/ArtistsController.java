package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArtistsController  implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private FlowPane artistsPanel;


    private List<Artist> allArtists = new ArrayList<>();


    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
        System.out.println("setUser - " + user.getID());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        VBox artistsVBox = new VBox();
        artistsVBox.setSpacing(50);

        Integer count = db.getDataInt("SELECT COUNT(*) FROM Artists;");
        for (int i=0; i<count; i++){
            int ID = i+1;
            String name = db.getDataString("SELECT name FROM Artists WHERE artist_id = \"" + ID + "\";");
            String surname = db.getDataString("SELECT surname FROM Artists WHERE artist_id = \"" + ID + "\";");
            String bio = db.getDataString("SELECT bio FROM Artists WHERE artist_id = \"" + ID + "\";");
            String birthDate = db.getDataString("SELECT birth_date FROM Artists WHERE artist_id = \"" + ID + "\";");
            String deathDate = db.getDataString("SELECT death_date FROM Artists WHERE artist_id = \"" + ID + "\";");
            Artist artist = new Artist(ID, name, surname, bio, birthDate, deathDate);
            allArtists.add(artist);

            //String artistInfo = artist.getName() + " " + artist.getSurname() + "\t(ur. "+ artist.getConvertedBirthDate() + (deathDate == null || deathDate.isEmpty() ? ")" : ", zm." + artist.getConvertedDeathDate() + ")");
            //artistInfo += "\n" + artist.getBio();

            Label nameLabel = new Label(artist.getName() + " " + artist.getSurname());
            nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            Label dateLabel = new Label("(ur. " + artist.getConvertedBirthDate() +
                    (deathDate == null || deathDate.isEmpty() ? ")" : ", zm." + artist.getConvertedDeathDate() + ")"));
            dateLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");

            Label bioLabel = new Label(artist.getBio());
            bioLabel.setWrapText(true);
            bioLabel.setMaxWidth(600);
            artistsVBox.getChildren().addAll(nameLabel, dateLabel, bioLabel);


            artistsVBox.setPadding(new Insets(0, 50, 0, 50));
            bioLabel.setPadding(new Insets(20, 0, 0, 20));

/*
            Label artistLabel = new Label(artistInfo);
            artistLabel.setPadding(new Insets(20, 50, 0, 50));
            artistLabel.setWrapText(true);
            artistLabel.setMaxWidth(800);
            artistsVBox.getChildren().add(artistLabel); */
            //artistsPanel.getChildren().add(artistLabel);
        }
        artistsPanel.getChildren().add(artistsVBox);

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String type = "LOG";
                if(user != null){
                    type = db.getDataString("SELECT user_type FROM users WHERE user_id = \"" + user.getID() + "\";");
                }
                //jeżeli admin
                switch (type){
                    case "ADM":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/AdminWindow.fxml", "Art Haven - Admin", user);
                        break;
                    case "CLI":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
                        break;
                    case "CRT":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
                        break;
                    case "MNG":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/ManagerWindow.fxml", "Art Haven - Manager", user);
                        break;
                    case "MRT":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/MarketingWindow.fxml", "Art Haven - Marketing", user);
                        break;
                    default:
                        sc.changeSceneUser(event, "/com/example/ArtGallery/hello-view.fxml", "Art Haven", user);
                        break;
                }
            }
        });
    }
}
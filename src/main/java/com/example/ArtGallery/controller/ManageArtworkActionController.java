package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageArtworkActionController implements Initializable {
    @FXML
    private Button editArtworkButton;
    @FXML
    private Button deleteArtworkButton;
    @FXML
    private Button cancelButton;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    private Artwork artwork;
    public void setUser(User user) {
        this.user = user;
    }

    public void setArtwork(Artwork artwork){this.artwork = artwork;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ChooseArtwork.fxml", "Manage Artwork - Art Curator", user);
            }
        });
        editArtworkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneWithUserAndArtwork(event, "/com/example/ArtGallery/EditArtwork.fxml", "Edit Artwork - Art Curator", user, artwork);
            }
        });
        deleteArtworkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.executeUpdate(db.getSt(), "DELETE FROM Artworks WHERE artwork_id = " + artwork.getID() + ";");
                db.executeUpdate(db.getSt(), "DELETE FROM ArtworkForSale WHERE artwork_id = " + artwork.getID() + ";");
                db.executeUpdate(db.getSt(), "UPDATE roomLayout SET artwork_id = 0 WHERE artwork_id = " + artwork.getID() + ";");
                sc.changeSceneWithUserAndArtwork(event, "/com/example/ArtGallery/ChooseArtwork.fxml", "Choose artwork to edit", user, artwork);
            }
        });

    }
}

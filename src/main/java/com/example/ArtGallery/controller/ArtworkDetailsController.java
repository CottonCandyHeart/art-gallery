package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;

public class ArtworkDetailsController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private FlowPane detailsPanel;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    private Artwork artwork;

    public void setUser(User user) {this.user = user;}
    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
        updateDetailsPanel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/Collection.fxml", "Artwork", user);
            }
        });
    }

    private void updateDetailsPanel() {
        if (artwork == null) {
            return;
        }

        ImageView imageView = new ImageView(new Image("file:" + artwork.getPicturePath()));
        imageView.setFitWidth(350);
        imageView.setPreserveRatio(true);
        FlowPane.setMargin(imageView, new Insets(20, 0, 0, 50));

        //VBox detailsBox = new VBox(10);
        Label titleLabel = new Label(artwork.getTitle());
        Label dateLabel = new Label(artwork.getCreationDate() + "r.");
        Label nameLabel = new Label(artwork.getArtist().getName() + " " + artwork.getArtist().getSurname() + ", ");
        Label methodLabel = new Label(artwork.getMethod());
        Label descriptionLabel = new Label(artwork.getDescription());

        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");
        titleLabel.setWrapText(true);

        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-style: italic;");
        dateLabel.setStyle("-fx-font-size: 16px; -fx-font-style: italic;");
        methodLabel.setStyle("-fx-font-size: 14px;");

        descriptionLabel.setStyle("-fx-font-size: 14px;");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(690);
        descriptionLabel.setStyle("-fx-text-alignment: justify;");

        Integer isOnSale = db.getDataInt("SELECT artwork_id FROM ArtworkForSale WHERE artwork_id = " + artwork.getID());
        Button buyButton = new Button("Kup");
        buyButton.setStyle("-fx-background-color: #e576a2;");
        buyButton.setOnAction(event -> {
            artwork.makeTransaction(db, user);
            buyButton.setText("Kupione!");
            buyButton.setDisable(true);
            buyButton.setStyle("-fx-opacity: 0.5; -fx-cursor: default; -fx-background-color: #e576a2;" );
        });

        HBox nameAndMethodBox = new HBox(10, nameLabel, dateLabel);

        VBox detailsBox;
        if (isOnSale != null && user != null){
            detailsBox = new VBox(10, titleLabel, nameAndMethodBox, methodLabel, buyButton);
        } else {
            detailsBox = new VBox(10, titleLabel, nameAndMethodBox, methodLabel);
        }

        detailsBox.setPadding(new Insets(100,0,0,20));
        detailsBox.setPrefWidth(300);

        VBox descriptionBox = new VBox(descriptionLabel);
        descriptionBox.setPadding(new Insets(0, 50, 0, 50));

        detailsPanel.getChildren().clear();
        detailsPanel.getChildren().addAll(imageView, detailsBox);
        //detailsPanel.setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        detailsPanel.setHgap(20);

        detailsPanel.setVgap(20);
        detailsPanel.getChildren().add(descriptionBox);
    }
}
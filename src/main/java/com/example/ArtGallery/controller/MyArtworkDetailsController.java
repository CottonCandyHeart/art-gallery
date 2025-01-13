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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MyArtworkDetailsController implements Initializable {
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
                sc.changeSceneUser(event, "/com/example/ArtGallery/MyArtwork.fxml", "Artwork", user);
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

        Label startDate = new Label("Data wystawienia: " + db.getDataString("SELECT startDate FROM ArtworkForSale WHERE artwork_id = " + artwork.getID()));
        Label price = new Label(db.getDataString("SELECT price FROM ArtworkForSale WHERE artwork_id = " + artwork.getID()) + " zł");

        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");
        titleLabel.setWrapText(true);

        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-style: italic;");
        dateLabel.setStyle("-fx-font-size: 16px; -fx-font-style: italic;");
        methodLabel.setStyle("-fx-font-size: 14px;");
        startDate.setStyle("-fx-font-size: 14px; -fx-text-fill: #e576a2;");
        price.setStyle("-fx-font-size: 14px; -fx-text-fill: #e576a2;");

        descriptionLabel.setStyle("-fx-font-size: 14px;");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(690);
        descriptionLabel.setStyle("-fx-text-alignment: justify;");

        Button cancelSaleButton = new Button("Anuluj sprzedaż");
        cancelSaleButton.setStyle("-fx-background-color: #e576a2;");
        cancelSaleButton.setOnAction(event -> {
            db.executeUpdate(db.getSt(), "DELETE FROM ArtworkForSale WHERE artwork_id =" + artwork.getID());
            sc.changeSceneWithUserAndArtwork(event, "/com/example/ArtGallery/MyArtworkDetails.fxml", "Artwork - Details", user, artwork);
        });


        VBox saleButtons;

        TextField amountField = new TextField();
        amountField.setPromptText("Podaj kwotę: ");

        Button acceptButton = new Button("Wystaw na sprzedaż");
        acceptButton.setStyle("-fx-background-color: #e576a2;");
        acceptButton.setOnAction(e ->{
            db.callProcedure("addArtworkForSale", artwork.getID(), user.getID(), Float.parseFloat(amountField.getText()));
            sc.changeSceneWithUserAndArtwork(e, "/com/example/ArtGallery/MyArtworkDetails.fxml", "Artwork - Details", user, artwork);
        });

        HBox nameAndMethodBox = new HBox(10, nameLabel, dateLabel);

        VBox detailsBox;
        Integer isOnSale = db.getDataInt("SELECT artwork_id FROM ArtworkForSale WHERE artwork_id = " + artwork.getID());

        if (isOnSale != null && user != null){
            detailsBox = new VBox(10, titleLabel, nameAndMethodBox, methodLabel, startDate, price, cancelSaleButton);
        } else {
            detailsBox = new VBox(10, titleLabel, nameAndMethodBox, methodLabel, amountField, acceptButton);
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
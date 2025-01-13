package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;

public class MapDetailsController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private FlowPane detailsPanel;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user = null;
    private int room;

    public void setUser(User user) {
        this.user = user;
    }
    public void setRoomNo(int num){
        this.room = num;
        switch(num){
            case 1:
                room1();
                break;
            case 2:
                room2();
                break;
            case 3:
                room3();
                break;
            case 4:
                room4();
                break;
            case 5:
                room5();
                break;
            case 6:
                room6();
                break;
            case 7:
                room7();
                break;
            case 8:
                room8();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                        sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map", user);
                        break;
                    case "CLI":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map", user);
                        break;
                    case "CRT":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map", user);
                        break;
                    case "MNG":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map", user);
                        break;
                    case "MRT":
                        sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map", user);
                        break;
                    default:
                        sc.changeSceneUser(event, "/com/example/ArtGallery/Map.fxml", "Map", user);
                        break;
                }
            }
        });
    }

    public void room1() {
        // Background square
        Rectangle square = new Rectangle(500, 500);
        square.setStroke(Color.BLACK);
        square.setFill(Color.TRANSPARENT);
        square.setStrokeWidth(2);

        detailsPanel.setAlignment(Pos.TOP_CENTER);
        detailsPanel.setPadding(new Insets(20,0,0,0));
        detailsPanel.getChildren().clear();

        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {100, 0},
                {300, 0},
                // Right edge (places 3-4)
                {400, 100},
                {400, 300},
                // Bottom edge (places 5-6)
                {300, 400},
                {100, 400},
                // Left edge (places 7-8)
                {0, 300},
                {0, 100}
        };

        // Place artworks
        for (int i = 0; i < 8; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != null) {
                Image artworkImage = new Image("file:src/main/resources/artwork" +
                        db.getDataString("SELECT picturePath FROM Artworks WHERE artwork_id =" + artwork_id));
                ImageView imageView = new ImageView(artworkImage);
                imageView.setFitWidth(placeSize - 10);
                imageView.setFitHeight(placeSize - 10);
                imageView.setPreserveRatio(true);

                // Position the artwork
                //imageView.setLayoutX(positions[i][0]);
                //imageView.setLayoutY(positions[i][1]);

                Button imageButton = new Button("", imageView);
                imageButton.setStyle("-fx-background-color: transparent;");
                imageButton.setLayoutX(positions[i][0]);
                imageButton.setLayoutY(positions[i][1]);

                imageButton.setOnAction(event -> {
                    sc.changeSceneMap(event, "/com/example/ArtGallery/MapArtworkDetails.fxml", "Map Details", user, artwork_id, room);
                });

                artworkPane.getChildren().add(imageButton);
            } else {
                Rectangle emptyPlace = new Rectangle(placeSize, placeSize);
                square.setStroke(Color.LIGHTPINK);
                square.setFill(Color.LIGHTPINK);

                artworkPane.getChildren().add(emptyPlace);
            }
        }

        mainPane.getChildren().addAll(square, artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room2(){

    }
    public void room3(){

    }
    public void room4(){

    }
    public void room5(){

    }
    public void room6(){

    }
    public void room7(){

    }
    public void room8(){

    }
}
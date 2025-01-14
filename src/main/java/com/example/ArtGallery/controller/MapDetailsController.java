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
        detailsPanel.setAlignment(Pos.TOP_CENTER);
        detailsPanel.setPadding(new Insets(20,0,0,0));
        detailsPanel.getChildren().clear();
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
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {100, 10},
                {300, 10},
                // Right edge (places 3-4)
                {400, 100},
                {400, 300},
                // Bottom edge (places 5-6)
                {300, 400},
                {100, 400},
                // Left edge (places 7-8)
                {10, 300},
                {10, 100}
        };

        // Place artworks
        for (int i = 0; i < 8; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(500,500), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room2(){
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {100, 10},
                {300, 10},
                // Right edge (places 3-4)
                {400, 100},
                {400, 300},
                // Bottom edge (places 5-6)
                {300, 400},
                {100, 400},
                // Left edge (places 7-8)
                {10, 300},
                {10, 100}
        };

        // Place artworks
        for (int i = 0; i < 8; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(500,500), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room3(){
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {20, 10},
                {160, 10},
                {320, 10},
                {480, 10},
                // Right edge (places 3-4)
                {490, 120},
                {490, 220},
                // Bottom edge (places 5-6)
                {480, 320},
                {320, 320},
                {160, 320},
                {10, 320},
                // Left edge (places 7-8)
                {10, 220},
                {10, 120}

        };

        // Place artworks
        for (int i = 0; i < 12; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(600,420), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room4(){
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {20, 10},
                {160, 10},
                {300, 10},
                {440, 10},
                {580, 10},
                // Right edge (places 3-4)
                {590, 150},
                {590, 300},
                // Bottom edge (places 5-6)
                {580, 420},
                {440, 420},
                {300, 420},
                {160, 420},
                {20, 420},
                // Left edge (places 7-8)
                {10, 300},
                {10, 150},
        };

        // Place artworks
        for (int i = 0; i < 14; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(700,520), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room5(){
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {75, 10},
                {200, 10},
                {325, 10},
                // Right edge (places 3-4)
                {400, 75},
                {400, 200},
                {400, 325},
                // Bottom edge (places 5-6)
                {325, 400},
                {200, 400},
                {75, 400},
                // Left edge (places 7-8)
                {10, 325},
                {10, 200},
                {10, 75}
        };

        // Place artworks
        for (int i = 0; i < 12; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(500,500), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room6(){
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {75, 10},
                {200, 10},
                {325, 10},
                // Right edge (places 3-4)
                {400, 75},
                {400, 200},
                {400, 325},
                // Bottom edge (places 5-6)
                {325, 400},
                {200, 400},
                {75, 400},
                // Left edge (places 7-8)
                {10, 325},
                {10, 200},
                {10, 75}
        };

        // Place artworks
        for (int i = 0; i < 12; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(500,500), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room7(){
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {100, 10},
                {250, 10},
                {400, 10},
                // Right edge (places 3-4)
                {490, 120},
                {490, 220},
                // Bottom edge (places 5-6)
                {400, 320},
                {250, 320},
                {100, 320},
                // Left edge (places 7-8)
                {10, 220},
                {10, 120}

        };

        // Place artworks
        for (int i = 0; i < 10; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(600,420), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }
    public void room8(){
        // Main container
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);

        // Container for artwork
        Pane artworkPane = new Pane();

        int placeSize = 100;
        // Positions for artwork (x, y coordinates for each place)
        double[][] positions = {
                // Top edge (places 1-2)
                {100, 10},
                {250, 10},
                {400, 10},
                // Right edge (places 3-4)
                {490, 120},
                {490, 220},
                // Bottom edge (places 5-6)
                {400, 320},
                {250, 320},
                {100, 320},
                // Left edge (places 7-8)
                {10, 220},
                {10, 120}

        };

        // Place artworks
        for (int i = 0; i < 10; i++) {
            int placeNo = i + 1;
            Integer artwork_id = db.getDataInt("SELECT artwork_id FROM roomLayout WHERE room_no = " + room + " AND place_no = " + placeNo);

            if (artwork_id != 0) {
                artworkPane.getChildren().add(getArtwork(placeSize, positions, i, artwork_id));
            } else {
                artworkPane.getChildren().add(emptyPlace(placeSize, positions, i));
            }
        }

        mainPane.getChildren().addAll(getRectangle(600, 420), artworkPane);
        detailsPanel.getChildren().add(mainPane);
    }

    private static Rectangle getRectangle(int w, int h) {
        Rectangle r = new Rectangle(w, h);
        r.setStroke(Color.BLACK);
        r.setFill(Color.TRANSPARENT);
        r.setStrokeWidth(2);
        return r;
    }

    public Button emptyPlace(int placeSize, double[][] positions, int i){
        Rectangle emptyPlace = new Rectangle(placeSize - 20, placeSize - 20);
        emptyPlace.setFill(Color.LIGHTPINK);

        Button imageButton = new Button("", emptyPlace);
        imageButton.setStyle("-fx-background-color: transparent;");
        imageButton.setLayoutX(positions[i][0]);
        imageButton.setLayoutY(positions[i][1]);

        if (user != null){
            String id = db.getDataString("SELECT user_type FROM Users WHERE user_id = \"" + user.getID() + "\";");
            if (id.equals("CRT")){
                imageButton.setOnAction(event -> {
                    sc.changeSceneMap(event, "/com/example/ArtGallery/MapCollection.fxml", "Choose artwork", user, (i+1), room);
                });
            }
        }

        return imageButton;
    }

    public Button getArtwork(int placeSize, double[][] positions, int i, int artwork_id){
        Image artworkImage = new Image("file:src/main/resources/artwork" +
                db.getDataString("SELECT picturePath FROM Artworks WHERE artwork_id =" + artwork_id + ";"));
        ImageView imageView = new ImageView(artworkImage);
        imageView.setFitWidth(placeSize - 10);
        imageView.setFitHeight(placeSize - 10);
        imageView.setPreserveRatio(true);

        // Position the artwork
        Button imageButton = new Button("", imageView);
        imageButton.setStyle("-fx-background-color: transparent;");
        imageButton.setLayoutX(positions[i][0]);
        imageButton.setLayoutY(positions[i][1]);

        imageButton.setOnAction(event -> {
            db.executeUpdate(db.getSt(), "UPDATE roomLayout SET artwork_id = 0 WHERE place_no = " + (i+1) + " AND room_no = " + room + ";");
            sc.changeSceneUserAndRoom(event, "/com/example/ArtGallery/MapDetails.fxml", "Map Details", user, room);
        });

        return imageButton;
    }
}
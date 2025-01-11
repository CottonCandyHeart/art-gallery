package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.net.URL;
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

    public void setUser(User user) {
        this.user = user;
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
}
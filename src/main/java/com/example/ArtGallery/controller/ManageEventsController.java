package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageEventsController implements Initializable {
    @FXML
    private Button editEventButton;
    @FXML
    private Button addEventButton;
    @FXML
    private Button deleteEventButton;
    @FXML
    private Button cancelButton12;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButton12.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManagerWindow.fxml", "Art Haven - Manager", user);
            }
        });
        editEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
<<<<<<< HEAD
                sc.changeSceneUser(event, "/com/example/ArtGallery/EditEvent.fxml", "Edit event - Manager", user);
=======
                sc.changeSceneUser(event, "/com/example/ArtGallery/EditEvent.fxml", "Edit Event - Manager", user);
>>>>>>> 4245e54078f367675aa5e3e4bc3d6fca8b87b21b
            }
        });
        addEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
<<<<<<< HEAD
                sc.changeSceneUser(event, "/com/example/ArtGallery/AddEvent.fxml", "Edit Event- Manager", user);
=======
                sc.changeSceneUser(event, "/com/example/ArtGallery/AddEvent.fxml", "Add Event - Manager", user);
>>>>>>> 4245e54078f367675aa5e3e4bc3d6fca8b87b21b
            }
        });
        deleteEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
<<<<<<< HEAD
                sc.changeSceneUser(event, "/com/example/ArtGallery/DeleteEvent.fxml", "Delete Event- Manager", user);
=======
                sc.changeSceneUser(event, "/com/example/ArtGallery/DeleteEvent.fxml", "Delete Event - Manager", user);
>>>>>>> 4245e54078f367675aa5e3e4bc3d6fca8b87b21b
            }
        });

    }
}

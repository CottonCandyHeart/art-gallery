package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteExhibitionController implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ChoiceBox exhibitionChoiceBox;
    @FXML
    private Label warningLabel;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> types = db.getDataStringList("SELECT name FROM exhibitions;");
        if(types.isEmpty()) {
            warningLabel.setText("No exhibitions to delete");
            confirmButton.setDisable(true);
        }else{
            exhibitionChoiceBox.getItems().addAll(types);
            exhibitionChoiceBox.setValue(types.get(0));
        }
        exhibitionChoiceBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

            }
        });

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Integer id = db.getDataInt("SELECT exhibition_id FROM exhibitions WHERE name = '" + exhibitionChoiceBox.getValue() + "';");
                db.executeUpdate(db.getSt(),"DELETE FROM exhibitions WHERE exhibition_id = " + id + ";");
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageExhibition.fxml", "Manage exhibition - Art Curator", user);
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageExhibition.fxml", "Manage exhibition - Art Curator", user);
            }
        });
    }
}

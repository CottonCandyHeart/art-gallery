package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EditExhibitionController implements Initializable {

    @FXML
    private ChoiceBox exhibitionChoiceBox;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private ChoiceBox locationChoiceBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> exhibitions = db.getDataStringList("SELECT name FROM exhibitions;");
        if(exhibitions.isEmpty()) {
            warningLabel.setText("No exhibitions to edit");
            confirmButton.setDisable(true);
        }else{
            exhibitionChoiceBox.getItems().addAll(exhibitions);
            exhibitionChoiceBox.setValue(exhibitions.get(0));
        }
        List<String> location = Arrays.asList("Sala 1", "Sala 2", "Sala 3", "Sala 4", "Sala 5", "Sala 6", "Sala 7", "Sala 8");
        locationChoiceBox.getItems().addAll(location);
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer id = db.getDataInt("SELECT exhibition_id FROM exhibitions WHERE name = '" + exhibitionChoiceBox.getValue() + "';");

                if(!nameTextField.getText().isEmpty()){
                    db.executeUpdate(db.getSt(),"UPDATE exhibitions SET name = '" + nameTextField.getText() + "' WHERE exhibition_id = " + id + ";");
                }

                if(!descriptionTextField.getText().isEmpty()){
                    db.executeUpdate(db.getSt(),"UPDATE exhibitions SET description = '" + descriptionTextField.getText() + "' WHERE exhibition_id = " + id + ";");
                }

                if(startDatePicker.getValue() != null){
                    db.executeUpdate(db.getSt(),"UPDATE exhibitions SET start_date = '" + startDatePicker.getValue() + "' WHERE exhibition_id = " + id + ";");
                }

                if(endDatePicker.getValue() != null){
                    db.executeUpdate(db.getSt(),"UPDATE exhibitions SET end_date = '" + endDatePicker.getValue() + "' WHERE exhibition_id = " + id + ";");
                }

                if(locationChoiceBox.getValue() != null){
                    db.executeUpdate(db.getSt(),"UPDATE exhibitions SET location = '" + locationChoiceBox.getValue() + "' WHERE exhibition_id = " + id + ";");
                }

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
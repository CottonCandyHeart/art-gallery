package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class    EditEventController implements Initializable {
    @FXML
    private Button confirmButton5;
    @FXML
    private Button cancelButton14;
    @FXML
    private Label warningLabel7;
    @FXML
    private ChoiceBox chooseEventChoiceBox;
    @FXML
    private TextField eventNameTextField1;
    @FXML
    private DatePicker eventDatePicker;
    @FXML
    private ChoiceBox exhibitionChoiceBox;
    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private TextField capacityTextField1;

    private DB db = new DB();
    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        List<String> types = db.getDataStringList("SELECT name FROM events ;");
        chooseEventChoiceBox.getItems().addAll(types);
        chooseEventChoiceBox.setValue(types.get(0));
         */
        confirmButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*
                String name = eventNameTextField1.getText();
                Integer capacity = Integer.parseInt(capacityTextField1.getText());
                 */

                //obsługa edycji eventu
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
            }
        });
        cancelButton14.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
            }
        });
    }
}

package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import com.example.ArtGallery.model.events.Event;
import com.example.ArtGallery.model.exhibitions.Exhibition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
    @FXML
    private Button confirmButton4;
    @FXML
    private Button cancelButton13;
    @FXML
    private Label warningLabel6;
    @FXML
    private TextField eventNameTextField;
    @FXML
    private DatePicker eventDatePicker;
    @FXML
    private ChoiceBox exhibitionChoiceBox;
    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private TextField capacityTextField;

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
            warningLabel6.setText("No exhibitions to add event");
            confirmButton4.setDisable(true);
        }else{
            exhibitionChoiceBox.getItems().addAll(exhibitions);
            exhibitionChoiceBox.setValue(exhibitions.get(0));
        }

        List<String> types = Arrays.asList("warsztat","oprowadzanie");
        typeChoiceBox.getItems().addAll(types);
        typeChoiceBox.setValue(types.get(0));

        confirmButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = eventNameTextField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate eventDate = eventDatePicker.getValue();
                String formattedEventDate = eventDate.format(formatter);
                String exhibitionName = exhibitionChoiceBox.getValue().toString();
                Integer exhibition_id = db.getDataInt("SELECT exhibition_id FROM exhibitions WHERE name LIKE '" + exhibitionName + "';");
                LocalDate ExhibitionStartDate = LocalDate.parse(db.getDataString("SELECT start_date FROM exhibitions WHERE exhibition_id = " + exhibition_id + ";"));
                LocalDate ExhibitionEndDate = LocalDate.parse(db.getDataString("SELECT end_date FROM exhibitions WHERE exhibition_id = " + exhibition_id + ";"));
                Integer capacity = Integer.parseInt(capacityTextField.getText());
                String type = typeChoiceBox.getValue().toString();

                String location = db.getDataString("SELECT location FROM exhibitions WHERE exhibition_id = " + exhibition_id + ";");
                String startDate = db.getDataString("SELECT start_date FROM exhibitions WHERE exhibition_id = " + exhibition_id + ";");
                String endDate = db.getDataString("SELECT end_date FROM exhibitions WHERE exhibition_id = " + exhibition_id + ";");
                String description = db.getDataString("SELECT description FROM exhibitions WHERE exhibition_id = " + exhibition_id + ";");
                Exhibition exhibition = new Exhibition(exhibition_id, exhibitionName, startDate, endDate, location, description);
                if(eventDate.isBefore(ExhibitionStartDate) || eventDate.isAfter(ExhibitionEndDate)) {
                    warningLabel6.setText("Event date must be between exhibition start and end date");
                }else{
                    Event event1 = new Event(-1, name, formattedEventDate, exhibition, capacity, type);
                    event1.addEvent(db);
                    sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
                }
                //obsługa dodania eventu
            }
        });
        cancelButton13.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/ManageEvents.fxml", "Manage events - Manager", user);
            }
        });
    }
}

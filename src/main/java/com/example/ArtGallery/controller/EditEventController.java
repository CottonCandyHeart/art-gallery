package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
        List<String> events = db.getDataStringList("SELECT name FROM events ;");
        if(events.isEmpty()) {
            warningLabel7.setText("No events to edit");
            confirmButton5.setDisable(true);
        }else{
            chooseEventChoiceBox.getItems().addAll(events);
            chooseEventChoiceBox.setValue(events.get(0));
        }

        List<String> exhibitions = db.getDataStringList("SELECT name FROM exhibitions;");
        exhibitionChoiceBox.getItems().addAll(exhibitions);

        List<String> types = Arrays.asList("warsztat","oprowadzanie");
        typeChoiceBox.getItems().addAll(types);
        confirmButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer id = db.getDataInt("SELECT event_id FROM events WHERE name = '" + chooseEventChoiceBox.getValue() + "';");

                if (!eventNameTextField1.getText().isEmpty()) {
                    db.executeUpdate(db.getSt(), "UPDATE events SET name = '" + eventNameTextField1.getText() + "' WHERE event_id = " + id + ";");
                }

                if (eventDatePicker.getValue() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate eventDate = eventDatePicker.getValue();
                    String formattedEventDate = eventDate.format(formatter);
                    if (exhibitionChoiceBox.getValue() == null) {
                        Integer exhibitionID = db.getDataInt("SELECT exhibition_id FROM events WHERE event_id = '" + id + "';");
                        LocalDate StartDate = db.getDataDate("SELECT start_date FROM exhibitions WHERE exhibition_id = '" + exhibitionID + "';").toLocalDate();
                        LocalDate EndDate = db.getDataDate("SELECT end_date FROM exhibitions WHERE exhibition_id = '" + exhibitionID + "';").toLocalDate();
                        if (eventDatePicker.getValue().isBefore(StartDate) || eventDatePicker.getValue().isAfter(EndDate)) {
                            warningLabel7.setText("Event date must be between exhibition start and end date");
                        } else {
                            db.executeUpdate(db.getSt(), "UPDATE events SET event_date = '" + formattedEventDate + "' WHERE event_id = " + id + ";");
                        }

                    }
                }

                    if (exhibitionChoiceBox.getValue() != null) {
                        Integer exhibitionID = db.getDataInt("SELECT exhibition_id FROM exhibitions WHERE name = '" + exhibitionChoiceBox.getValue() + "';");
                        LocalDate StartDate = db.getDataDate("SELECT start_date FROM exhibitions WHERE exhibition_id = '" + exhibitionID + "';").toLocalDate();
                        LocalDate EndDate = db.getDataDate("SELECT end_date FROM exhibitions WHERE exhibition_id = '" + exhibitionID + "';").toLocalDate();
                        if (eventDatePicker.getValue() == null) {
                            warningLabel7.setText("Event date must be between exhibition start and end date");
                            System.out.println("Event date must be between exhibition start and end date");
                        } else if (eventDatePicker.getValue().isBefore(StartDate) || eventDatePicker.getValue().isAfter(EndDate)) {
                            warningLabel7.setText("Event date must be between exhibition start and end date");
                            System.out.println("Event date must be between exhibition start and end date");
                        } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate eventDate1 = eventDatePicker.getValue();
                            String formattedEventDate1 = eventDate1.format(formatter);
                            db.executeUpdate(db.getSt(), "UPDATE events SET exhibition_id = '" + exhibitionID + "' WHERE event_id = " + id + ";");
                            db.executeUpdate(db.getSt(), "UPDATE events SET event_date = '" + formattedEventDate1 + "' WHERE event_id = " + id + ";");
                        }
                    }

                    if (!capacityTextField1.getText().isEmpty()) {
                        db.executeUpdate(db.getSt(), "UPDATE events SET capacity = '" + capacityTextField1.getText() + "' WHERE event_id = " + id + ";");
                    }

                    if (typeChoiceBox.getValue() != null) {
                        db.executeUpdate(db.getSt(), "UPDATE events SET event_type = '" + typeChoiceBox.getValue() + "' WHERE event_id = " + id + ";");
                    }
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

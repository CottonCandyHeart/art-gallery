package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

import com.example.ArtGallery.model.events.Event;

public class EventsController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;
    @FXML
    private Text month;
    @FXML
    private FlowPane calendar;
    @FXML
    private Button cancelButton2;
    @FXML
    private Button rightButton;
    @FXML
    private Button leftButton;


    private SceneController sc = new SceneController();
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();

        cancelButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.changeScene(event, "/com/example/ArtGallery/hello-view.fxml", "Welcome!");
            }
        });

        rightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dateFocus = dateFocus.plusMonths(1);
                calendar.getChildren().clear();
                drawCalendar();
            }
        });

        leftButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dateFocus = dateFocus.minusMonths(1);
                calendar.getChildren().clear();
                drawCalendar();
            }
        });
    }

    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //Lista eventów na dany miesiąc
        Map<Integer, List<Event>> calendarEventsMap = getCalendarEventsMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<Event> calendarActivities = calendarEventsMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarEvents(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.PINK);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarEvents (List<Event> calendarEvents, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarEventsBox = new VBox();
        for (int k = 0; k < calendarEvents.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarEventsBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //po naciśnięciu okna wszystkie informacje na temat eventu się wyświetlają w konsoli
                    System.out.println(calendarEvents);
                });
                break;
            }
            Text text = new Text(calendarEvents.get(k).getName() + ", " + calendarEvents.get(k).getEventDate().toLocalTime());
            calendarEventsBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //tekst po naciśnięciu przycisku
                System.out.println(text.getText());
            });
        }
        calendarEventsBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarEventsBox.setMaxWidth(rectangleWidth * 0.8);
        calendarEventsBox.setMaxHeight(rectangleHeight * 0.65);
        calendarEventsBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarEventsBox);
    }
    private Map<Integer, List<Event>> getCalendarEventsMonth(ZonedDateTime dateFocus){
        List<Event> calendarEvents = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        //pobranie z bazy listy eventów na dany miesiąc i rok
        return createCalendarEventsMap(calendarEvents);
    }
    private Map<Integer, List<Event>> createCalendarEventsMap(List<Event> calendarEvents){
        Map<Integer, List<Event>> CalendarEventsMap = new HashMap<>();

        for(Event event: calendarEvents){
            int eventdate = event.getEventDate().getDayOfMonth();
            //if(!createCalendarEventsMap().containsKey(eventdate)){

            //}
        }
        return CalendarEventsMap;
    }
}

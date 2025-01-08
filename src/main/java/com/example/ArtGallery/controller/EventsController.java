package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.exhibitions.Exhibition;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import com.example.ArtGallery.model.events.Event;

import static java.lang.Integer.parseInt;

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

    private DB db = new DB();
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
                //jeżeli admin
                sc.changeSceneUser(event, "/com/example/ArtGallery/AdminWindow.fxml", "Art Haven - Admin", user);
                //jeżeli client
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ClientWindow.fxml", "Art Haven", user);
                //jeżeli kurator
                //sc.changeSceneUser(event, "/com/example/ArtGallery/CuratorWindow.fxml", "Art Haven - Art Curator", user);
                //jeżeli manager
                //sc.changeSceneUser(event, "/com/example/ArtGallery/ManagerWindow.fxml", "Art Haven - Manager", user);
                //jeżeli marketingowca
                //sc.changeSceneUser(event, "/com/example/ArtGallery/MarketingWindow.fxml", "Art Haven - Marketing", user);
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
                double rectangleWidth =(calendarWidth/7) - strokeWidth*2 - spacingH*7;
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
            Event event = calendarEvents.get(k);
            Text text = new Text("- "+event.getName());
            text.setWrappingWidth(rectangleWidth * 0.8);
            calendarEventsBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //tekst po naciśnięciu przycisku
                System.out.println("Event: " + event.getName());
                System.out.println("Date: " + event.getEventDate());
                System.out.println("Exhibition: " + event.getExhibition().getName());
                System.out.println("Exhibition description: " + event.getExhibition().getDescription());
                System.out.println("Capacity: " + event.getCapacity());
                System.out.println("Type: " + event.getType());
                //System.out.println(text.getText());
            });
        }

        calendarEventsBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarEventsBox.setMaxWidth(rectangleWidth * 0.8);
        calendarEventsBox.setMaxHeight(rectangleHeight * 0.65);
        calendarEventsBox.setStyle("-fx-background-color:#FFB6C1");
        stackPane.getChildren().add(calendarEventsBox);
    }
    private Map<Integer, List<Event>> getCalendarEventsMonth(ZonedDateTime dateFocus) {
        List<Event> calendarEvents = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        Integer count = db.getDataInt("SELECT COUNT(*) FROM events WHERE YEAR(event_date) = " + year + " AND MONTH(event_date) = " + month + ";");
        for(int i  = 0; i < count; i++) {
            int eventID = i + 1;
            String name = db.getDataString("SELECT name FROM events WHERE event_id = \"" + eventID + "\";");
            String date = db.getDataString("SELECT event_date FROM events WHERE event_id = \"" + eventID + "\";");
            /*
            // Wyświtla się dobra data, ale nie ma czasu, więc jest ustawiony 00:00:00 i na sztywno strefa czasowa Europe/Warsaw
            LocalDate localDate = LocalDate.parse(date);
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("Europe/Warsaw"));*/
            int exhibitionID = db.getDataInt("SELECT exhibition_id FROM events WHERE event_id = \"" + eventID + "\";");
            int capacity = db.getDataInt("SELECT capacity FROM events WHERE event_id = \"" + eventID + "\";");
            String type = db.getDataString("SELECT event_type FROM events WHERE event_id = ?", eventID);
            String exhibitionName = db.getDataString("SELECT name FROM exhibitions WHERE exhibition_id = \"" + exhibitionID + "\";");
            String exhibitionStartDate = db.getDataString("SELECT start_date FROM exhibitions WHERE exhibition_id = \"" + exhibitionID + "\";");
            String exhibitionEndDate = db.getDataString("SELECT end_date FROM exhibitions WHERE exhibition_id = \"" + exhibitionID + "\";");
            String exhibitionLocation = db.getDataString("SELECT location FROM exhibitions WHERE exhibition_id = \"" + exhibitionID + "\";");
            String exhibitionDescription = db.getDataString("SELECT description FROM exhibitions WHERE exhibition_id = \"" + exhibitionID + "\";");
            Exhibition exhibition = new Exhibition(exhibitionID, exhibitionName, exhibitionStartDate, exhibitionEndDate, exhibitionLocation, exhibitionDescription);
            Event event = new Event(eventID, name, date, exhibition, capacity, type);
            calendarEvents.add(event);

        }
        //pobranie z bazy listy eventów na dany miesiąc i rok
        return createCalendarEventsMap(calendarEvents);
    }
    private Map<Integer, List<Event>> createCalendarEventsMap(List<Event> calendarEvents){
        Map<Integer, List<Event>> CalendarEventsMap = new HashMap<>();

        for (Event event : calendarEvents) {
            String[] partsOfDate = event.getEventDate().split("-");
            int eventDay = parseInt(partsOfDate[2]);
            CalendarEventsMap.computeIfAbsent(eventDay, k -> new ArrayList<>()).add(event);
        }
        /*for(Event event: calendarEvents){
            int eventdate = event.getEventDate().getDayOfMonth();
            //if(!createCalendarEventsMap().containsKey(eventdate)){

            //}
        }*/
        return CalendarEventsMap;
    }
}

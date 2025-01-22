package com.example.ArtGallery.controller;

import com.example.ArtGallery.db.DB;
import com.example.ArtGallery.model.artists.Artist;
import com.example.ArtGallery.model.artworks.Artwork;
import com.example.ArtGallery.model.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportDetailsController implements Initializable {
    @FXML
    private Button cancelButton8;
    @FXML
    private FlowPane galleryPanel;
    private DB db = new DB();

    private SceneController sc = new SceneController();
    private User user;
    private String reportName;
    private String reportDetails;
    public void setUser(User user) {
        this.user = user;
    }
    public void setReportName(String rn) {this.reportName = rn;}
    public void setReportDetails(String rd) {
        this.reportDetails = rd;

        Label label1 = new Label();
        Label label2 = new Label();

        label1.setText(reportName);
        label2.setText(reportDetails);

        label1.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");
        label2.setWrapText(true);
        label2.setPadding(new Insets(30,0,0,0));

        galleryPanel.setPadding(new Insets(50, 50, 50, 50));
        galleryPanel.setOrientation(Orientation.VERTICAL);

        galleryPanel.getChildren().add(label1);
        galleryPanel.getChildren().add(label2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButton8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sc.changeSceneUser(event, "/com/example/ArtGallery/GenerateRaports.fxml", "Generate Reports", user);
            }
        });
    }

}


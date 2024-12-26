package com.example.ArtGallery.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.ArtGallery.users.*;

public class SceneController {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, User user){
        Parent root = null;
        try{
            root = FXMLLoader.load(SceneController.class.getResource(fxmlFile));
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 816, 600));
        stage.show();
    }
}
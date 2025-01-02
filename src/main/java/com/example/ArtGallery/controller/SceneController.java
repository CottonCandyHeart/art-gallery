package com.example.ArtGallery.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.ArtGallery.model.users.*;

public class SceneController {
    public static void changeScene(ActionEvent event, String fxmlFile, String title){
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

    public static void changeSceneUser(ActionEvent event, String fxmlFile, String title, User user){
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource(fxmlFile));
            Parent root = loader.load();

            if(fxmlFile == "\"/com/example/ArtGallery/ClientWindow.fxml\"") {
                ClientWindowController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/MyAccount.fxml\"") {
                MyAccountController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/Collection.fxml\"") {
                CollectionController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/UserEdit.fxml\"") {
                UserEditController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/Map.fxml\"") {
                MapController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/Artists.fxml\"") {
                ArtistsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/AdminWindow.fxml\"") {
                ArtistsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/ManageUsers.fxml\"") {
                ArtistsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/EditUsersData.fxml\"") {
                ArtistsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/AddUser.fxml\"") {
                ArtistsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile == "\"/com/example/ArtGallery/Events.fxml\"") {
                ArtistsController controller = loader.getController();
                controller.setUser(user);
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

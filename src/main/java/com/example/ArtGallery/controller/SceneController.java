package com.example.ArtGallery.controller;

import com.example.ArtGallery.model.artworks.Artwork;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

            if(fxmlFile.equals("/com/example/ArtGallery/ClientWindow.fxml")) {
                ClientWindowController controller = loader.getController();
                if (user != null)
                    System.out.println("Scene controller - " + user.getID());
                else
                    System.out.println("Scene controller - no user");
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/MyAccount.fxml")) {
                MyAccountController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/Collection.fxml")) {
                CollectionController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/UserEdit.fxml")) {
                UserEditController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/Map.fxml")) {
                MapController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/Artists.fxml")) {
                ArtistsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/AdminWindow.fxml")) {
                AdminWindowController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/ManageUsers.fxml")) {
                ManageUsersController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/EditUsersData.fxml")) {
                EditUsersDataController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/AddUser.fxml")) {
                AddUserController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/DeleteUser.fxml")) {
                DeleteUserController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/Events.fxml")) {
                EventsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/CuratorWindow.fxml")) {
                CuratorWindowController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/ChooseArtwork.fxml")) {
                ChooseArtworkController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/EditArtwork.fxml")) {
                EditArtworkController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/AddArtwork.fxml")) {
                AddArtworkController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/GenerateRaports.fxml")) {
                GenerateRaportsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/MapDetails.fxml")) {
                MapDetailsController controller = loader.getController();
                controller.setUser(user);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/ArtworkDetails.fxml")) {
                ArtworkDetailsController controller = loader.getController();
                controller.setUser(user);
            } else if(fxmlFile.equals("/com/example/ArtGallery/MyArtwork.fxml")) {
                MyArtworkController controller = loader.getController();
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

    public static void changeSceneWithUserAndArtwork(ActionEvent event, String fxmlFile, String title, User user, Artwork artwork) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource(fxmlFile));
            Parent root = loader.load();

            if(fxmlFile.equals("/com/example/ArtGallery/ArtworkDetails.fxml")) {
                ArtworkDetailsController controller = loader.getController();
                controller.setUser(user);
                controller.setArtwork(artwork);
            }
            else if(fxmlFile.equals("/com/example/ArtGallery/MyArtworkDetails.fxml")) {
                MyArtworkDetailsController controller = loader.getController();
                controller.setUser(user);
                controller.setArtwork(artwork);
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

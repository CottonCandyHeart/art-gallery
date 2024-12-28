package com.example.ArtGallery;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.WebApplicationType;

public class StartingWindow extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        try {
            springContext = new SpringApplicationBuilder(ArtGalleryApplication.class)
                    .headless(false)
                    .web(WebApplicationType.NONE)
                    .run(getParameters().getRaw().toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            fxmlLoader.setControllerFactory(springContext::getBean);
            Scene scene = new Scene(fxmlLoader.load(), 816, 600);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @Override
    public void stop() {
        if (springContext != null) {
            springContext.close();
        }
    }

    public static void main(String[] args) {
        // Just launch the JavaFX application
        launch(args);
    }
}
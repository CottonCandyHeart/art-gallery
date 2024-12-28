module com.example.ArtGallery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires javafx.base;
    requires javafx.graphics;

    // Add these additional Spring requirements
    requires spring.beans;
    requires spring.core;

    exports com.example.ArtGallery.controller;
    opens com.example.ArtGallery.controller;

    opens com.example.ArtGallery to spring.beans, spring.context, javafx.fxml, spring.core, javafx.base;
    exports com.example.ArtGallery;

}


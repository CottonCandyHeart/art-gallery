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
    requires jakarta.persistence;
    requires spring.data.jpa;
    requires org.hibernate.orm.core;
    requires spring.orm;

    exports com.example.ArtGallery.controller;
    opens com.example.ArtGallery.controller;

    opens com.example.ArtGallery to spring.beans, spring.context, javafx.fxml, spring.core, javafx.base;
    exports com.example.ArtGallery;

    opens com.example.ArtGallery.model to spring.core, org.hibernate.orm.core;

    exports com.example.ArtGallery.data;
    opens com.example.ArtGallery.data to spring.beans, spring.core, spring.context, org.hibernate.orm.core;

    exports com.example.ArtGallery.services;

    requires java.base;

}


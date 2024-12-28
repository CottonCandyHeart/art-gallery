module com.example.ArtGallery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.ArtGallery.controller;
    opens com.example.ArtGallery.controller;

    opens com.example.ArtGallery to javafx.fxml;
    exports com.example.ArtGallery;
}


module com.example.ArtGallery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;

    exports com.example.ArtGallery.controller;
    opens com.example.ArtGallery.controller;

    opens com.example.ArtGallery to javafx.fxml;
    exports com.example.ArtGallery;

    exports com.example.ArtGallery.model.users;
    exports com.example.ArtGallery.model.exhibitions;
    exports com.example.ArtGallery.model.artists;
    exports com.example.ArtGallery.model.artworks;
    exports com.example.ArtGallery.model.events;
    exports com.example.ArtGallery.model.marketing;
    exports com.example.ArtGallery.model.reports;
    exports com.example.ArtGallery.model.transactions;
}


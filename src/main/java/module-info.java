module com.example.art_gallery {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.art_gallery to javafx.fxml;
    exports com.example.art_gallery;
}
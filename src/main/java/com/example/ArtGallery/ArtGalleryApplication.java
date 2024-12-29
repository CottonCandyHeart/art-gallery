package com.example.ArtGallery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// te dopiski w nawiasie są do wywalenia potem
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ArtGalleryApplication {
    // Configuration class
}
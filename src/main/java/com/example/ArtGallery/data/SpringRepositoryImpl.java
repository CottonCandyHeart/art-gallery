package com.example.ArtGallery.data;

import com.example.ArtGallery.model.Spring;
import org.springframework.stereotype.Repository;

@Repository
public class SpringRepositoryImpl implements SpringRepository {
    public SpringRepositoryImpl() {  // Public constructor
        // constructor code
    }
    @Override
    public Spring getSpring() {
        Spring spring = new Spring("Hello Spring");
        return spring;
    }
}

package com.example.ArtGallery.services;

import com.example.ArtGallery.data.SpringRepository;
import com.example.ArtGallery.model.Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringServiceImpl implements SpringService {

    private SpringRepository springRepository;

    @Autowired
    public SpringServiceImpl(SpringRepository springRepository){
        this.springRepository=springRepository;
    }


    @Override
    public Spring getSpring() {
        return springRepository.getSpring();
    }
}

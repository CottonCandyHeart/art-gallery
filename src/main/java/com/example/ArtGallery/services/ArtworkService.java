package com.example.ArtGallery.services;

import com.example.ArtGallery.model.Artwork;
import com.example.ArtGallery.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    public Artwork findArtworkById(Long id) {
        return artworkRepository.findById(id).orElse(null);
    }

    public void addArtwork(Artwork artwork) {
        artworkRepository.save(artwork);
    }

    public void deleteArtwork(Long id) {
        artworkRepository.deleteById(id);
    }
}

package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.model.Image;
import com.team19.carmicroservice.repository.ImageRepository;
import com.team19.carmicroservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}

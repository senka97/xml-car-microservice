package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.model.Image;
import com.team19.carmicroservice.repository.ImageRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public Image save(Image image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        Image img = imageRepository.save(image);
        logger.info(MessageFormat.format("P-ID:{0}-created;UserID:{1}",img.getId(), cp.getUserID()));

        return img;
    }
}

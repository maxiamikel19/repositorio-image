package com.amikel.maxi.repositorioimage.application.images;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amikel.maxi.repositorioimage.domain.entity.Image;
import com.amikel.maxi.repositorioimage.domain.exception.ObjectSearchNotFoundException;
import com.amikel.maxi.repositorioimage.domain.service.ImageService;
import com.amikel.maxi.repositorioimage.infra.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Image save(Image image) {
        Image newImage = imageRepository.save(image);
        return newImage;
    }

    @Override
    public Image getById(String imageId) {
        Optional<Image> optionalImage = imageRepository.findById(imageId);
        return optionalImage.orElseThrow( () -> new ObjectSearchNotFoundException("Image id not found"));
    }
    
}

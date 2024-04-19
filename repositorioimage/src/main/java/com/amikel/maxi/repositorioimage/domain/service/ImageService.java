package com.amikel.maxi.repositorioimage.domain.service;

import com.amikel.maxi.repositorioimage.domain.entity.Image;

public interface ImageService {
    
    Image save(Image image);
    Image getById(String imageId);
    
}

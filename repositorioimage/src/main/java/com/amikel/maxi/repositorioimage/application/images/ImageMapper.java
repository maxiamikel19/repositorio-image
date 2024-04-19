package com.amikel.maxi.repositorioimage.application.images;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amikel.maxi.repositorioimage.domain.entity.Image;
import com.amikel.maxi.repositorioimage.domain.enums.ImageExtension;

@Component
public class ImageMapper {
    
    public Image mapToImage(MultipartFile file, String name, List<String> tags) throws IOException{
        Image image = Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();
        return image;
    }
}

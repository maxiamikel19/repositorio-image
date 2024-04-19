package com.amikel.maxi.repositorioimage.application.images;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amikel.maxi.repositorioimage.domain.entity.Image;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/images")
@RequiredArgsConstructor
public class ImageController {
    
    private final ImageServiceImpl imageService;
    private final ImageMapper imageMapper;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException{

        Image newImage = imageMapper.mapToImage(file, name, tags);

        URI uri = imageUrl(newImage);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/teste")
    public ResponseEntity<String> testeUrl(){
        return ResponseEntity.ok().body("Private URL");
    }

    private URI imageUrl(Image image){
        String imagepath = "/" + image.getId();
        return ServletUriComponentsBuilder.fromCurrentRequest().path(imagepath).build().toUri();
    }
}

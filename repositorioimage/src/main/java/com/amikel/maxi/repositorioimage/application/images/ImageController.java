package com.amikel.maxi.repositorioimage.application.images;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/images")
public class ImageController {
    
    @PostMapping
    public ResponseEntity<String> testePost(){
        return ResponseEntity.ok().body("Teste 22");
    }

    @GetMapping("/teste")
    public ResponseEntity<String> testeUrl(){
        return ResponseEntity.ok().body("Public URL");
    }
}

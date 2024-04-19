package com.amikel.maxi.repositorioimage.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amikel.maxi.repositorioimage.domain.entity.Image;

public interface ImageRepository extends JpaRepository<Image, String>{
    
}

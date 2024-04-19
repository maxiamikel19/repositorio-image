package com.amikel.maxi.repositorioimage.domain.enums;

import java.util.Arrays;

import org.springframework.http.MediaType;

public enum ImageExtension {
    PNG(MediaType.IMAGE_PNG),
    JPEG(MediaType.IMAGE_JPEG),
    GIFF(MediaType.IMAGE_GIF);

    private MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtension valueOf(MediaType mediaType){
        return Arrays.stream(values()).filter(imgextension -> imgextension.mediaType.equals(mediaType)).findFirst().orElse(null);
    }
}

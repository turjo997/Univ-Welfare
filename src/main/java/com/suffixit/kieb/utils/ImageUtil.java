package com.suffixit.kieb.utils;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtil {
    public static ResponseEntity<InputStreamResource> getInputStreamResourceResponseEntity(String imageName, String imageDirectory) throws FileNotFoundException {
        if (imageName != null) {
            Path imagePath = Paths.get(imageDirectory, imageName);

            if (Files.exists(imagePath)) {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(imagePath.toFile()));

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Image not found");
            }
        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Image name is null");
        }
    }
}

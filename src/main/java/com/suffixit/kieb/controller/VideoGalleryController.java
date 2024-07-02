package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.ConvertedVideoGalleryDTO;
import com.suffixit.kieb.dto.VideoGalleryDTo;
import com.suffixit.kieb.entities.VideoGalleryInfo;
import com.suffixit.kieb.service.VideoGalleryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/video-gallery")
public class VideoGalleryController {
    private VideoGalleryService videoGalleryService;

    public VideoGalleryController(VideoGalleryService videoGalleryService) {
        this.videoGalleryService = videoGalleryService;
    }


    @PostMapping("/admin/postVideo")
    public ResponseEntity<String>postVideo( VideoGalleryDTo videoGalleryDTo) throws IOException {
        return ResponseEntity.ok(  videoGalleryService.postVideo(videoGalleryDTo));
    }

    @GetMapping("/getById")
    public ResponseEntity<VideoGalleryInfo>viewGalleryById(@RequestParam Integer id){
     return ResponseEntity.ok(videoGalleryService.getById(id));

    }


    @PutMapping("/admin/update-gallery")
    public ResponseEntity<String>updateGallery( VideoGalleryDTo videoGalleryDTo) throws IOException {
        return ResponseEntity.ok(videoGalleryService.updateGallery(videoGalleryDTo));
    }
    @GetMapping("/getBy-category")
    public ResponseEntity<Page<ConvertedVideoGalleryDTO>>findByCategory(@RequestParam Integer catId,
                                                               @RequestParam(defaultValue = "0")int pageNo,
                                                               @RequestParam(defaultValue = "10")int size){
        Pageable pageable=PageRequest.of(pageNo,size);
        return ResponseEntity.ok(videoGalleryService.getByCategory(catId,pageable));
    }
}

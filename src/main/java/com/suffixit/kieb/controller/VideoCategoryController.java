package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.AddVideoCategoryDTO;
import com.suffixit.kieb.dto.UpdateVideoCategoryDTO;
import com.suffixit.kieb.entities.VideoCategory;
import com.suffixit.kieb.service.VideoCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/video-category")
public class VideoCategoryController {
    private final VideoCategoryService videoCategoryService;

    public VideoCategoryController(VideoCategoryService videoCategoryService) {
        this.videoCategoryService = videoCategoryService;
    }


    @PostMapping("/admin/create")
    public ResponseEntity<String> createVideoCategory(AddVideoCategoryDTO addVideoCategoryDTO) throws IOException {
        return ResponseEntity.ok(videoCategoryService.createVideoCategory(addVideoCategoryDTO));

    }

    @GetMapping("/getAll")
    public Page<VideoCategory> getAll(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return videoCategoryService.getAll(pageable);
    }

    @GetMapping("/admin/getById")
    public ResponseEntity<VideoCategory> getByCatId(@RequestParam int catId) {
        return ResponseEntity.ok(videoCategoryService.getByCatId(catId));
    }

    @PutMapping("/admin/update-videoCategory")
    public ResponseEntity<String> updateVideoCategory(UpdateVideoCategoryDTO updateVideoCategoryDTO) throws IOException {
        videoCategoryService.updateVideoCategory(updateVideoCategoryDTO);
        return ResponseEntity.ok("event Category updated successfully");
    }


}

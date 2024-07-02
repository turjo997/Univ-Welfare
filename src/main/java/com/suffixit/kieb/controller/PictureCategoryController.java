package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.*;
import com.suffixit.kieb.service.PictureCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class PictureCategoryController {

    private final PictureCategoryService pictureCategoryService;

    public PictureCategoryController(PictureCategoryService pictureCategoryService) {
        this.pictureCategoryService = pictureCategoryService;
    }

    @PostMapping("/add/album/image")
    public ResponseEntity<String> addPictureCategory(@Valid @ModelAttribute PictureGalleryDTO pictureGalleryDTO){
        return pictureCategoryService.addPictureCategory(pictureGalleryDTO);
    }

    @PostMapping("/add/existingAlbum/image")
    public ResponseEntity<String> addAlbumInExistingPictureCategory(@Valid @ModelAttribute PictureGalleryDTO pictureGalleryDTO){
        return pictureCategoryService.addAlbumInExistingPictureCategory(pictureGalleryDTO);
    }

    @PostMapping("/add/album/subject")
    public ResponseEntity<?> addSubjectByAlbum(@Valid @RequestBody PictureCategoryDTO pictureCategoryDTO) {
        return pictureCategoryService.addSubjectByAlbum(pictureCategoryDTO);
    }

    @PostMapping("/add/album/picture")
    public ResponseEntity<String> addPictureByAlbum(@Valid @ModelAttribute PictureGalleryInfoDTOs pictureGalleryInfoDTOs) throws Exception{
        return pictureCategoryService.addPictureByAlbum(pictureGalleryInfoDTOs);
    }

    @GetMapping("/get/album/subject")
    public List<AlbumAndSubjectDTO> getAllAlbumAndSubject(){
        return pictureCategoryService.getAllAlbumAndSubject();
    }

    @GetMapping("/get/all/album")
    public List<PhotoAlbumDTO> getAllPhotoAlbum(){
        return pictureCategoryService.getAllPhotoAlbum();
    }
}

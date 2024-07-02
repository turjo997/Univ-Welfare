package com.suffixit.kieb.service;


import com.suffixit.kieb.dto.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PictureCategoryService {

    ResponseEntity<String> addPictureCategory(PictureGalleryDTO pictureGalleryDTO);


    ResponseEntity<?> addSubjectByAlbum(PictureCategoryDTO pictureCategoryDTO);

    ResponseEntity<String> addAlbumInExistingPictureCategory(PictureGalleryDTO pictureGalleryDTO);

    ResponseEntity<String> addPictureByAlbum(PictureGalleryInfoDTOs pictureGalleryInfoDTOs) throws IOException;

    ResponseEntity<InputStreamResource> getAllImagesForCategory(String imageCategory , String pictureName) throws FileNotFoundException;

    List<ImageInfo> ImagePathByCategory();

    List<AlbumAndSubjectDTO> getAllAlbumAndSubject();

    List<PhotoAlbumDTO> getAllPhotoAlbum();

    List<PictureDTO> getAllPicturesByAlbum(Integer albumId);


}

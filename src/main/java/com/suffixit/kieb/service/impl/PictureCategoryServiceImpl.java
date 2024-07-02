package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.*;

import com.suffixit.kieb.entities.*;
import com.suffixit.kieb.repository.*;
import com.suffixit.kieb.service.PictureCategoryService;
import com.suffixit.kieb.utils.FileUploadUtil;
import com.suffixit.kieb.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Validated
public class PictureCategoryServiceImpl implements PictureCategoryService {

    private final PictureCategoryRepository pictureCategoryRepository;
    private final PictureGalleryInfoRepository pictureGalleryInfoRepository;

    private final AlbumDivisionRepository albumDivisionRepository;

    private final UniversitySubjectRepository universitySubjectRepository;

    private final String uploadDir;


    public PictureCategoryServiceImpl(PictureCategoryRepository pictureCategoryRepository,
                                      PictureGalleryInfoRepository pictureGalleryInfoRepository, MemberDivisionRepository memberDivisionRepository,
                                      AlbumDivisionRepository albumDivisionRepository
               , UniversitySubjectRepository universitySubjectRepository, @Value("${fileStore.directory}") String uploadDir) {
        this.pictureCategoryRepository = pictureCategoryRepository;
        this.pictureGalleryInfoRepository = pictureGalleryInfoRepository;
        this.albumDivisionRepository = albumDivisionRepository;
        this.universitySubjectRepository = universitySubjectRepository;
        this.uploadDir = uploadDir;
    }


    @Override
    public ResponseEntity<String> addPictureCategory(PictureGalleryDTO pictureGalleryDTO) {

        if (pictureCategoryRepository.existsByCategoryName(pictureGalleryDTO.getPictureCategory())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category with this name already exists");
        }

        if (pictureCategoryRepository.existsByCategoryShortName(pictureGalleryDTO.getPictureCategoryShortName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category with this short name already exists");
        }

        PictureCategory pictureCategory = PictureCategory.builder()
                .categoryName(pictureGalleryDTO.getPictureCategory())
                .categoryShortName(pictureGalleryDTO.getPictureCategoryShortName())
                .build();

        pictureCategoryRepository.save(pictureCategory);


        List<PictureGalleryInfo> galleryDTOS = new ArrayList<>();

        for (PictureGalleryInfoDTO pictureGalleryInfoDTO : pictureGalleryDTO.getPictureGalleryInfoDTOList()) {

            try {

                if (!FileUploadUtil.isImageFile(pictureGalleryInfoDTO.getImage1())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only image files are allowed.");
                }

                String categoryName = "/album_division/" + pictureCategory.getCategoryName();
                Path categoryDir = createCategoryDirectory(categoryName);
                String fileName = "Image" + "_" + pictureGalleryInfoDTO.getImage1().getOriginalFilename();

                saveImage(categoryDir, fileName, pictureGalleryInfoDTO.getImage1());

                PictureGalleryInfo pictureGalleryInfoObj = PictureGalleryInfo.builder()
                        .pictureCategory(pictureCategory)
                        .pictureTitle(pictureGalleryInfoDTO.getPictureTitle())
                        .image1(fileName)
                        .build();

                galleryDTOS.add(pictureGalleryInfoObj);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Picture upload failed!");
            }

        }
        pictureGalleryInfoRepository.saveAll(galleryDTOS);


        return ResponseEntity.ok("Picture category is created successfully with Album images!");

    }

    @Override
    public ResponseEntity<?> addSubjectByAlbum(PictureCategoryDTO pictureCategoryDTO)    {

        PictureCategory pictureCategory = pictureCategoryRepository.findById(pictureCategoryDTO.getAlbumId())
                .orElse(null);
        if (pictureCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("PictureCategory not found with ID: " + pictureCategoryDTO.getAlbumId());
        }

        UniversitySubject subject = universitySubjectRepository.findBySubjectCode(pictureCategoryDTO.getSubjectCode());


        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Subject not found with Code: " + pictureCategoryDTO.getSubjectCode());
        }

//        if (albumDivisionRepository.existsByAlbumAndSubject(pictureCategory, subject)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("MemberDivision is already added to PictureCategory with ID: " + pictureCategoryDTO.getAlbumId());
//        }

        AlbumDivision albumDivision = AlbumDivision.builder()
                .album(pictureCategory)
                //.subject(subject)
                .build();

        albumDivisionRepository.save(albumDivision);
        return ResponseEntity.ok("Division added to album successfully");

    }

    @Override
    public ResponseEntity<String> addAlbumInExistingPictureCategory(PictureGalleryDTO pictureGalleryDTO){

        Integer pictureCategoryId = pictureGalleryDTO.getPictureCategoryId();

        PictureCategory pictureCategory = pictureCategoryRepository.findPictureCategoryById(pictureCategoryId);

        if(pictureCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Picture Category with this id is not found!");
        }

            List<PictureGalleryInfo> galleryDTOS = new ArrayList<>();

            for (PictureGalleryInfoDTO pictureGalleryInfoDTO : pictureGalleryDTO.getPictureGalleryInfoDTOList()) {

                try {

                    if (!FileUploadUtil.isImageFile(pictureGalleryInfoDTO.getImage1())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only image files are allowed.");
                    }

                    String categoryName = "/album_division/" + pictureCategory.getCategoryName();
                    Path categoryDir = createCategoryDirectory(categoryName);
                    String fileName = "Image" + "_" + pictureGalleryInfoDTO.getImage1().getOriginalFilename();

                    saveImage(categoryDir, fileName, pictureGalleryInfoDTO.getImage1());

                    PictureGalleryInfo pictureGalleryInfoObj = PictureGalleryInfo.builder()
                            .pictureCategory(pictureCategory)
                            .pictureTitle(pictureGalleryInfoDTO.getPictureTitle())
                            .image1(fileName)
                            .build();

                    galleryDTOS.add(pictureGalleryInfoObj);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Picture upload failed!");
                }

            }
            pictureGalleryInfoRepository.saveAll(galleryDTOS);

            return ResponseEntity.ok("Album images  is created successfully in Picture Category!");

    }

    @Override
    public ResponseEntity<String> addPictureByAlbum(PictureGalleryInfoDTOs pictureGalleryInfoDTOs) throws IOException {

        PictureCategory pictureCategory = pictureCategoryRepository.findById(pictureGalleryInfoDTOs.getCategoryId())
                .orElse(null);

        if (pictureCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("PictureCategory not found with ID: " + pictureGalleryInfoDTOs.getCategoryId());
        }

        if (!FileUploadUtil.isImageFile(pictureGalleryInfoDTOs.getImage())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only image files are allowed.");
        }


        String categoryName = "/album_division/" + pictureCategory.getCategoryName();
        Path categoryDir = createCategoryDirectory(categoryName);
        String ImageFileName1 = "Image" + "_" + pictureGalleryInfoDTOs.getImage().getOriginalFilename();

        saveImage(categoryDir, ImageFileName1, pictureGalleryInfoDTOs.getImage());

        PictureGalleryInfo pictureGalleryInfo = PictureGalleryInfo.builder()
                .pictureCategory(pictureCategory)
                .pictureTitle(pictureGalleryInfoDTOs.getPictureTitle())
                .image1(ImageFileName1)
                .build();

        pictureGalleryInfoRepository.save(pictureGalleryInfo);

        return ResponseEntity.ok("Picture is added successfully");
    }

    @Override
    public ResponseEntity<InputStreamResource> getAllImagesForCategory(String imageCategory , String pictureName) {

        try {
            String imageDirectory = uploadDir + "/album_division/" + imageCategory;

            return ImageUtil.getInputStreamResourceResponseEntity(pictureName, imageDirectory);

        } catch (IOException e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Something bad occurred");
        }

    }

    @Override
    public List<ImageInfo> ImagePathByCategory() {

        List<Object[]> result = pictureGalleryInfoRepository.findAllCategoriesWithFirstImage();

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"No pictures found");
        }

        return result.stream()
                .map(row -> ImageInfo.builder()
                        .id((Integer) row[0])
                        .categoryName((String) row[1])
                        .fileName((String) row[2])
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public List<AlbumAndSubjectDTO> getAllAlbumAndSubject() {

        List<PictureCategory> pictureCategories = pictureCategoryRepository.findAll();

        if (pictureCategories.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"No picture category is found");
        }

        List<AlbumAndSubjectDTO> albumList = new ArrayList<>();

        for (PictureCategory pictureCategory : pictureCategories) {
            Integer albumId = pictureCategory.getId();
            String albumName = pictureCategory.getCategoryName();
            String albumShortName = pictureCategory.getCategoryShortName();

            //List<SubjectDTO> subjectDTOS = new ArrayList<>();

            //List<AlbumDivision> albumDivisions = albumDivisionRepository.findByAlbumId(albumId);

            /*for (AlbumDivision albumDivision : albumDivisions) {
                SubjectDTO subjectDTO = SubjectDTO.builder()
                        .subjectId(albumDivision.getSubject().getId())
                        .subjectName(albumDivision.getSubject().getSubjectShortName())
                        .subjectCode(albumDivision.getSubject().getSubjectCode())
                        .build();
                subjectDTOS.add(subjectDTO);
            }*/

            AlbumAndSubjectDTO albumAndSubjectDTO = AlbumAndSubjectDTO.builder()
                    .albumId(albumId)
                    .albumName(albumName)
                    .albumShortName(albumShortName)
                    //.subjectDTOS(subjectDTOS)
                    .build();

            albumList.add(albumAndSubjectDTO);
        }

        return albumList;

    }

    @Override
    public List<PhotoAlbumDTO> getAllPhotoAlbum() {
        List<PictureCategory> albumInfos = pictureCategoryRepository.findAll();

        if (albumInfos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "No album found");
        }

        return albumInfos.stream()
                .map(album -> PhotoAlbumDTO.builder()
                        .value(album.getId())
                        .title(album.getCategoryName())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public List<PictureDTO> getAllPicturesByAlbum(Integer albumId) {

        PictureCategory pictureCategory = pictureCategoryRepository.findById(albumId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "Album not found"));

        List<PictureGalleryInfo> pictureGalleryInfos = pictureGalleryInfoRepository.findByPictureCategory(Optional.ofNullable(pictureCategory));

        Map<String, PictureDTO> albumImageMap = new HashMap<>();

        for (PictureGalleryInfo info : pictureGalleryInfos) {
            String albumName = info.getPictureCategory().getCategoryName();

            PictureDTO pictureDTO = albumImageMap.get(albumName);

            if (pictureDTO == null) {
                pictureDTO = PictureDTO.builder()
                        .albumName(albumName)
                        .imageInfo(new ArrayList<>())
                        .build();
                albumImageMap.put(albumName, pictureDTO);
            }

            ImageDTO imageDTO = ImageDTO.builder()
                    .imageId(info.getId())
                    .imageTitle(info.getPictureTitle())
                    .image(info.getImage1())
                    .build();

            pictureDTO.getImageInfo().add(imageDTO);
        }

        return new ArrayList<>(albumImageMap.values());
    }

    private Path createCategoryDirectory(String categoryName) throws IOException {
        Path categoryDir = Paths.get(uploadDir, categoryName);
        return Files.createDirectories(categoryDir);
    }

    private void saveImage(Path categoryDir, String fileName, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            FileUploadUtil.saveFile(categoryDir.toString(), fileName, image);
        }
    }



}


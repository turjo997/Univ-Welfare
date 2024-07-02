package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.AddVideoCategoryDTO;
import com.suffixit.kieb.dto.UpdateVideoCategoryDTO;
import com.suffixit.kieb.entities.VideoCategory;
import com.suffixit.kieb.repository.VideoCategoryRepository;
import com.suffixit.kieb.service.VideoCategoryService;
import com.suffixit.kieb.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VideoCategoryServiceIMPL implements VideoCategoryService {
    private final String uploadDir;
    private final VideoCategoryRepository videoCategoryRepository;

    public VideoCategoryServiceIMPL(@Value("${fileStore.directory}") String uploadDir, VideoCategoryRepository videoCategoryRepository) {
        this.uploadDir = uploadDir;
        this.videoCategoryRepository = videoCategoryRepository;
    }

    @Override
    public String createVideoCategory(AddVideoCategoryDTO add) throws IOException {
        Optional<VideoCategory> videoCategory = videoCategoryRepository.findByCategoryShortName(add.getCategoryShortName());
        if (videoCategory.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category shortName already exists.");
        }
        if (!FileUploadUtil.isImageFile(add.getImage1())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only image files allowed");
        }

        String categoryName = "/video/" + add.getCategoryName();
        Path categoryDir = createCategoryDirectory(categoryName);
        String imageName = add.getImage1().getOriginalFilename();
        saveImage(categoryDir, imageName, add.getImage1());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        VideoCategory category = new VideoCategory();
        category.setCategoryName(add.getCategoryName());
        category.setCategoryShortName(add.getCategoryShortName());
        category.setImage1(imageName);
        category.setAddDate(LocalDateTime.now().toString());
        category.setAddUser(authentication.getName());
        videoCategoryRepository.save(category);
        return "Video category created successfully";
    }

    @Override
    public Page<VideoCategory> getAll(Pageable pageable) {
        return videoCategoryRepository.findAll(pageable);
    }

    @Override
    public VideoCategory getByCatId(int catId) {
        return videoCategoryRepository.findById(catId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "video category id not found"));
    }

    @Override
    public String updateVideoCategory(UpdateVideoCategoryDTO updateCategory) throws IOException {
        Optional<VideoCategory> optional = videoCategoryRepository.findById(updateCategory.getId());
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category id not found" + updateCategory.getId());

        }
        boolean image = FileUploadUtil.isImageFile(updateCategory.getImage1());
        if (!image) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only image files allowed");
        }
        String categoryName = "/video/" + optional.get().getCategoryName();
        Path categoryDir = createCategoryDirectory(categoryName);
        String imageName = updateCategory.getImage1().getOriginalFilename();
        saveImage(categoryDir, imageName, updateCategory.getImage1());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        VideoCategory category = optional.get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setCategoryShortName(updateCategory.getCategoryShortName());
        category.setImage1(imageName);
        category.setModDate(LocalDateTime.now().toString());
        category.setModUser(authentication.getName());
        videoCategoryRepository.save(category);
        return "video Category updated..";


    }


    private Path createCategoryDirectory(String categoryName) throws IOException {
        Path categoryDir = Paths.get(uploadDir, categoryName);
        return Files.createDirectories(categoryDir);
    }

    private void saveImage(Path categoryDir, String fileName, MultipartFile image1) throws IOException {
        if (image1 != null && !image1.isEmpty()) {
            FileUploadUtil.saveFile(categoryDir.toString(), fileName, image1);
        }
    }
}

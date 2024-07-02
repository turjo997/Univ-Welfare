package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.ConvertedVideoGalleryDTO;
import com.suffixit.kieb.dto.VideoGalleryDTo;
import com.suffixit.kieb.entities.VideoCategory;
import com.suffixit.kieb.entities.VideoGalleryInfo;
import com.suffixit.kieb.repository.VideoCategoryRepository;
import com.suffixit.kieb.repository.VideoGalleryRepository;
import com.suffixit.kieb.service.VideoGalleryService;
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
public class VideoGalleryServiceIMPL implements VideoGalleryService {
    private final String uploadDir;
    private final VideoCategoryRepository videoCategoryRepository;
    private final VideoGalleryRepository videoGalleryRepository;

    public VideoGalleryServiceIMPL(@Value("${user.home}/kieb") String uploadDir, VideoCategoryRepository videoCategoryRepository, VideoGalleryRepository videoGalleryRepository) {
        this.uploadDir = uploadDir;
        this.videoCategoryRepository = videoCategoryRepository;
        this.videoGalleryRepository = videoGalleryRepository;
    }

    @Override
    public String postVideo(VideoGalleryDTo videoGalleryDTo) throws IOException {
        VideoCategory videoCategory = videoCategoryRepository.findById(videoGalleryDTo.getVideoCategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "videoCategory id not found"));
        Optional<VideoGalleryInfo> videoGalleryInfo = videoGalleryRepository.findByVideoTitle(videoGalleryDTo.getVideoTitle());
        if (videoGalleryInfo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "given video title already exist");
        }
        if (!FileUploadUtil.isImageFile(videoGalleryDTo.getFeatureImage())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only image file allow");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String title = "/video/" + videoGalleryDTo.getVideoTitle();
        String imageName = videoGalleryDTo.getFeatureImage().getOriginalFilename();
        Path videoPath = createvideoPath(title);
        saveImage(videoPath, imageName, videoGalleryDTo.getFeatureImage());
        VideoGalleryInfo gallery = new VideoGalleryInfo();
        gallery.setVideoTitle(videoGalleryDTo.getVideoTitle());
        gallery.setVideoCaption(videoGalleryDTo.getVideoCaption());
        gallery.setAddDate(LocalDateTime.now().toString());
        gallery.setAddUser(authentication.getName());
        gallery.setVideoCategory(videoCategory);
        gallery.setFeatureImage(imageName);
        gallery.setPublished(videoGalleryDTo.getPublished());
        gallery.setVideoOrginalLink(videoGalleryDTo.getVideoOrginalLink());
        gallery.setVideoEmdedLink(videoGalleryDTo.getVideoEmdedLink());
        videoGalleryRepository.save(gallery);
        return "video gallery created successfully";


    }

    @Override
    public VideoGalleryInfo getById(Integer id) {
        return videoGalleryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "video Gallery id not found"));
    }

    @Override
    public Page<ConvertedVideoGalleryDTO> viewAll(Pageable pageable) {
        Page<VideoGalleryInfo> newVideoGallery = videoGalleryRepository.findAll(pageable);
        if (newVideoGallery.isEmpty()) {
            return Page.empty();
        }
        return newVideoGallery.map(this::newDto);
    }

    @Override
    public String updateGallery(VideoGalleryDTo videoGalleryDTo) throws IOException {
        VideoGalleryInfo UpdateGallery = videoGalleryRepository.findById(videoGalleryDTo.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "video Gallery id not found"));
        VideoCategory cat = videoCategoryRepository.findById(videoGalleryDTo.getVideoCategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "videoCategory id not found"));
        Optional<VideoGalleryInfo> optional = videoGalleryRepository.findByVideoTitle(videoGalleryDTo.getVideoTitle());
        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "given video title already exist");
        }
        if (!FileUploadUtil.isImageFile(videoGalleryDTo.getFeatureImage())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only image file allow");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String title = "/video/" + videoGalleryDTo.getVideoTitle();
        String imageName = videoGalleryDTo.getFeatureImage().getOriginalFilename();
        Path videoPath = createvideoPath(title);
        saveImage(videoPath, imageName, videoGalleryDTo.getFeatureImage());
        UpdateGallery.setVideoTitle(videoGalleryDTo.getVideoTitle());
        UpdateGallery.setVideoCaption(videoGalleryDTo.getVideoCaption());
        UpdateGallery.setAddDate(LocalDateTime.now().toString());
        UpdateGallery.setAddUser(authentication.getName());
        UpdateGallery.setVideoCategory(cat);
        UpdateGallery.setFeatureImage(imageName);
        UpdateGallery.setPublished(videoGalleryDTo.getPublished());
        UpdateGallery.setVideoOrginalLink(videoGalleryDTo.getVideoOrginalLink());
        UpdateGallery.setVideoEmdedLink(videoGalleryDTo.getVideoEmdedLink());
        videoGalleryRepository.save(UpdateGallery);
        return "video gallery updated";

    }

    @Override
    public Page<ConvertedVideoGalleryDTO> getByCategory(Integer catId, Pageable pageable) {
        VideoCategory videoCategory = videoCategoryRepository.findById(catId).orElseThrow(null);
        Page<VideoGalleryInfo> videoGalleryInfo = videoGalleryRepository.findByVideoCategoryOrderByVideoTitle(videoCategory, pageable);
        return videoGalleryInfo.map(this::newDto);
    }


    private Path createvideoPath(String videoTitle) throws IOException {
        Path VideoGalleryDir = Paths.get(uploadDir, videoTitle);
        return Files.createDirectories(VideoGalleryDir);
    }

    private void saveImage(Path categoryDir, String fileName, MultipartFile featureImage) throws IOException {
        if (featureImage != null && !featureImage.isEmpty()) {
            FileUploadUtil.saveFile(categoryDir.toString(), fileName, featureImage);
        }
    }

    private ConvertedVideoGalleryDTO newDto(VideoGalleryInfo videoGalleryInfo) {
        ConvertedVideoGalleryDTO dto = new ConvertedVideoGalleryDTO();
        dto.setId(videoGalleryInfo.getId());
        dto.setVideoCategoryId(videoGalleryInfo.getVideoCategory().getId());
        dto.setAddDate(videoGalleryInfo.getAddDate());
        dto.setVideoOrginalLink(videoGalleryInfo.getVideoOrginalLink());
        dto.setVideoEmdedLink(videoGalleryInfo.getVideoEmdedLink());
        dto.setVideoCaption(videoGalleryInfo.getVideoCaption());
        dto.setVideoTitle(videoGalleryInfo.getVideoTitle());
        dto.setPublished(videoGalleryInfo.getPublished());
        dto.setAddUser(videoGalleryInfo.getAddUser());
        dto.setModDate(videoGalleryInfo.getModDate());
        dto.setModUser(videoGalleryInfo.getModUser());
        return dto;
    }


}

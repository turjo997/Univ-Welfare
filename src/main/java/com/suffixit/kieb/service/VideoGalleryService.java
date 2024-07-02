package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.ConvertedVideoGalleryDTO;
import com.suffixit.kieb.dto.VideoGalleryDTo;
import com.suffixit.kieb.entities.VideoGalleryInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface VideoGalleryService {
    String postVideo(VideoGalleryDTo videoGalleryDTo) throws IOException;

    VideoGalleryInfo getById(Integer id);

    Page<ConvertedVideoGalleryDTO> viewAll(Pageable pageable);

    String updateGallery(VideoGalleryDTo videoGalleryDTo) throws IOException;

    Page<ConvertedVideoGalleryDTO> getByCategory(Integer catId ,Pageable pageable);
}

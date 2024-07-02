package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.AddVideoCategoryDTO;
import com.suffixit.kieb.dto.UpdateVideoCategoryDTO;
import com.suffixit.kieb.entities.EventCategory;
import com.suffixit.kieb.entities.VideoCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface VideoCategoryService {
    String createVideoCategory(AddVideoCategoryDTO addVideoCategoryDTO) throws IOException;

    Page<VideoCategory> getAll(Pageable pageable);

    VideoCategory getByCatId(int catId);

    String updateVideoCategory(UpdateVideoCategoryDTO updateVideoCategoryDTO) throws IOException;
}

package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.VideoCategory;
import com.suffixit.kieb.entities.VideoGalleryInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoGalleryRepository extends JpaRepository<VideoGalleryInfo,Integer> {
    Optional<VideoGalleryInfo> findByVideoTitle(String videoTitle);

    Page<VideoGalleryInfo> findByVideoCategoryOrderByVideoTitle(VideoCategory videoCategory, Pageable pageable);
}

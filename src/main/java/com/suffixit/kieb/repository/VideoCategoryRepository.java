package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoCategoryRepository extends JpaRepository<VideoCategory,Integer> {
    Optional<VideoCategory> findByCategoryShortName(String categoryShortName);
}

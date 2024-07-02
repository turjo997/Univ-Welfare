package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.PictureCategory;
import com.suffixit.kieb.entities.PictureGalleryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureGalleryInfoRepository extends JpaRepository<PictureGalleryInfo , Integer> {

    @Query("SELECT p.pictureCategory.id, p.pictureCategory.categoryName, p.image1 FROM PictureGalleryInfo p WHERE p.id IN (SELECT MIN(pg.id) FROM PictureGalleryInfo pg GROUP BY pg.pictureCategory)")
    List<Object[]> findAllCategoriesWithFirstImage();

    List<PictureGalleryInfo> findByPictureCategory(Optional<PictureCategory> pictureCategory);

}

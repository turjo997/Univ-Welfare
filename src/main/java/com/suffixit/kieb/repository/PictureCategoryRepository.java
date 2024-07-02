package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.PictureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureCategoryRepository extends JpaRepository<PictureCategory , Integer> {
    boolean existsByCategoryName(String categoryName);

    boolean existsByCategoryShortName(String categoryShortName);

    PictureCategory findPictureCategoryById(Integer pictureCategoryId);
}

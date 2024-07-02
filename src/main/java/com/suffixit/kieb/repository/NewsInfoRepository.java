package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.NewsCategory;
import com.suffixit.kieb.entities.NewsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsInfoRepository extends JpaRepository<NewsInfo , Integer> {
    Page<NewsInfo> findAllByNewsCategoryOrderByNewsTitle(NewsCategory newsCategory, Pageable pageable);
Optional<NewsInfo> findByNewsTitle(String title);

    Optional<NewsInfo> findByNewsCategoryId(Integer newsId);

    Optional<NewsInfo> findByNewsCategory_IdAndId(Integer categoryId, Integer id);
}

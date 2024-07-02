package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsCategoryRepository extends JpaRepository<NewsCategory,Integer> {
    NewsCategory findByCategoryShortName(String categoryShortName);

}

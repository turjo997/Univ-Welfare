package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.NewsCategoryDTO;
import com.suffixit.kieb.entities.NewsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface NewsCategoryService {


    Page<NewsCategory> getAll(Pageable pageable);

    NewsCategory getByCatId(int catId);

    String updateNewsCategory(NewsCategoryDTO newsCategoryDTO);
}

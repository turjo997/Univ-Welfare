package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.NewsCategoryDTO;
import com.suffixit.kieb.entities.NewsCategory;
import com.suffixit.kieb.repository.NewsCategoryRepository;
import com.suffixit.kieb.service.NewsCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {
    private final NewsCategoryRepository newsCategoryRepository;

    public NewsCategoryServiceImpl(NewsCategoryRepository newsCategoryRepository) {
        this.newsCategoryRepository = newsCategoryRepository;
    }



    @Override
    public Page<NewsCategory> getAll(Pageable pageable) {
        return newsCategoryRepository.findAll(pageable);
    }

    @Override
    public NewsCategory getByCatId(int catId) {
        return newsCategoryRepository.findById(catId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "news category id not found"));
    }

    @Override
    public String updateNewsCategory(NewsCategoryDTO dTO) {
        NewsCategory category = newsCategoryRepository.findById(dTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "news cat id not found"));
        category.setCategoryName(dTO.getCategoryShortName());
        category.setCategoryName(dTO.getCategoryName());
        newsCategoryRepository.save(category);
        return "news category updated successfully";

    }
}

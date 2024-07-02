package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.NewsCategoryDTO;
import com.suffixit.kieb.entities.NewsCategory;
import com.suffixit.kieb.service.NewsCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news-category")
public class NewsCategoryController {
    private final NewsCategoryService newsCategoryService;

    public NewsCategoryController(NewsCategoryService newsCategoryService) {
        this.newsCategoryService = newsCategoryService;
    }




    @GetMapping("/getAll")
    public Page<NewsCategory> getAll(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return newsCategoryService.getAll(pageable);
    }

    @GetMapping("/getById")
    public ResponseEntity<NewsCategory> getByCatId(@RequestParam int catId) {
        return ResponseEntity.ok(newsCategoryService.getByCatId(catId));
    }

    @PutMapping("/admin/update-eventCategory")
    public ResponseEntity<String> updateNewsCategory(@RequestBody NewsCategoryDTO newsCategoryDTO) {
      return ResponseEntity.ok(newsCategoryService.updateNewsCategory(newsCategoryDTO));

    }

}

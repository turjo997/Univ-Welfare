package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.CommitteeCategoryDTO;
import com.suffixit.kieb.entities.CommitteeCategory;
import com.suffixit.kieb.repository.CommitteeCategoryRepository;
import com.suffixit.kieb.service.CommitteeCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommitteeCategoryServiceImpl implements CommitteeCategoryService {


    private final CommitteeCategoryRepository committeeCategoryRepository;

    public CommitteeCategoryServiceImpl(CommitteeCategoryRepository committeeCategoryRepository) {
        this.committeeCategoryRepository = committeeCategoryRepository;
    }
    @Override
    public ResponseEntity<String> addCommitteeCategory(CommitteeCategoryDTO committeeCategoryDTO) {

        if (committeeCategoryRepository.existsByCategoryName(committeeCategoryDTO.getCategoryName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category with this name already exists");
        }

        if (committeeCategoryRepository.existsByCategoryShortName(committeeCategoryDTO.getCategoryShortName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category with this short name already exists");
        }

        CommitteeCategory committeeCategory = CommitteeCategory.builder()
                .categoryName(committeeCategoryDTO.getCategoryName())
                .categoryShortName(committeeCategoryDTO.getCategoryShortName())
                .build();

        committeeCategoryRepository.save(committeeCategory);

        return ResponseEntity.ok("Committee Category created successfully!");
    }





}

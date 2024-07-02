package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.CommitteeCategoryDTO;
import com.suffixit.kieb.service.CommitteeCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/committeeCategory")
public class CommitteeCategoryController {

    private final CommitteeCategoryService committeeCategoryService;


    public CommitteeCategoryController(CommitteeCategoryService committeeCategoryService) {
        this.committeeCategoryService = committeeCategoryService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addCommitteeCategory(@RequestBody CommitteeCategoryDTO committeeCategoryDTO){

        return committeeCategoryService.addCommitteeCategory(committeeCategoryDTO);

    }

}

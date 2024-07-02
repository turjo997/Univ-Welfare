package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.CommitteeCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CommitteeCategoryService {


    ResponseEntity<String> addCommitteeCategory(CommitteeCategoryDTO committeeCategoryDTO);

}

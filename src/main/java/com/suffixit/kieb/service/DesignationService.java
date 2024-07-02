package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.DesignationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface DesignationService {

    ResponseEntity<String> addDesignation(DesignationDTO designationDTO);

    ResponseEntity<?> getAllDesignation();
}

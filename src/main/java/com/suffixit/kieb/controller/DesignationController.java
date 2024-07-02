package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.DesignationDTO;
import com.suffixit.kieb.service.DesignationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/designation")
public class DesignationController {

    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addDesignation(@Valid @RequestBody DesignationDTO designationDTO){

        System.out.println("designationDTO ");

        return designationService.addDesignation(designationDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDesignation(){
        return designationService.getAllDesignation();
    }

}

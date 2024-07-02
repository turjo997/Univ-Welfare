package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.DegreeTypeDTO;
import com.suffixit.kieb.service.DegreeTypeService;
import com.suffixit.kieb.service.YearService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class DegreeController {

    private final DegreeTypeService degreeTypeService;

    public DegreeController(YearService yearService, DegreeTypeService degreeTypeService) {

        this.degreeTypeService = degreeTypeService;
    }

    @GetMapping("/degree-type/all")
    public List<DegreeTypeDTO> getAllDegreeType() {
        return degreeTypeService.getAll();
    }

}

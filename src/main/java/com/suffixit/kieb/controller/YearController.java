package com.suffixit.kieb.controller;

import com.suffixit.kieb.entities.Year;
import com.suffixit.kieb.service.YearService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/year")
public class YearController {

    private final YearService yearService;

    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    @GetMapping("/all")
    public List<Year> getAllYear() {
        return yearService.getAll();
    }
}

package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.UniversitySubjectDTO;
import com.suffixit.kieb.service.UniversitySubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/universitySubject")
public class UniversitySubjectController {

    private final UniversitySubjectService universitySubjectService;

    public UniversitySubjectController(UniversitySubjectService universitySubjectService) {
        this.universitySubjectService = universitySubjectService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addUniversitySubject(@RequestBody UniversitySubjectDTO universitySubjectDTO){
        return universitySubjectService.addUniversitySubject(universitySubjectDTO);
    }

    @GetMapping("/all")
    public List<UniversitySubjectDTO> getAllSubject(){
        return universitySubjectService.getAllSubject();
    }




}

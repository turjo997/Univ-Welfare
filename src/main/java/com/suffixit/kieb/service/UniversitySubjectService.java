package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.UniversitySubjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UniversitySubjectService {

    ResponseEntity<String> addUniversitySubject(UniversitySubjectDTO universitySubjectDTO);

    List<UniversitySubjectDTO> getAllSubject();

}

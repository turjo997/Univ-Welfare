package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.UniversitySubjectDTO;
import com.suffixit.kieb.entities.UniversitySubject;
import com.suffixit.kieb.repository.UniversitySubjectRepository;
import com.suffixit.kieb.service.UniversitySubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversitySubjectServiceImpl implements UniversitySubjectService {

    private final UniversitySubjectRepository universitySubjectRepository;

    public UniversitySubjectServiceImpl(UniversitySubjectRepository universitySubjectRepository) {
        this.universitySubjectRepository = universitySubjectRepository;
    }

    @Override
    public ResponseEntity<String> addUniversitySubject(UniversitySubjectDTO universitySubjectDTO) {
        UniversitySubject universitySubject = UniversitySubject.builder().subjectShortName(universitySubjectDTO.getSubjectShortName()).subjectLongName(universitySubjectDTO.getSubjectLongName()).build();

        universitySubjectRepository.save(universitySubject);

        return ResponseEntity.ok("University Subject added successfully!");
    }

    @Override
    public List<UniversitySubjectDTO> getAllSubject() {
        List<UniversitySubject> subjectInfos = universitySubjectRepository.findAll();

        if (subjectInfos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No social links found");
        }

        return subjectInfos.stream().map(subject -> UniversitySubjectDTO.builder().id(subject.getId()).subjectLongName(subject.getSubjectLongName()).subjectShortName(subject.getSubjectShortName()).subjectCode(subject.getSubjectCode()).build()).collect(Collectors.toList());
    }
}

package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.repository.UniversityRepository;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository){

        this.universityRepository = universityRepository;
    }

}

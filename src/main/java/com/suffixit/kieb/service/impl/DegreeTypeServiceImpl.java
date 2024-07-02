package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.DegreeTypeDTO;
import com.suffixit.kieb.dto.SocialLinkProfileDTO;
import com.suffixit.kieb.entities.Degree;
import com.suffixit.kieb.entities.SocialLink;
import com.suffixit.kieb.repository.DegreeRepository;
import com.suffixit.kieb.service.DegreeTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DegreeTypeServiceImpl implements DegreeTypeService {

    private final DegreeRepository degreeRepository;

    public DegreeTypeServiceImpl(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public List<DegreeTypeDTO> getAll() {
        List<Degree> degreeTypeInfos = degreeRepository.findAll();

        if (degreeTypeInfos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"No degree types found");
        }

        return degreeTypeInfos.stream()
                .map(degree -> DegreeTypeDTO.builder()
                        .id(degree.getId())
                        .degreeName(degree.getDegreeName())
                        .build())
                .collect(Collectors.toList());
    }
}

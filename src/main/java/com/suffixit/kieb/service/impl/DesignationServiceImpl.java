package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.DesignationDTO;
import com.suffixit.kieb.entities.Committee;
import com.suffixit.kieb.entities.CommitteeDesignation;
import com.suffixit.kieb.repository.CommitteeRepository;
import com.suffixit.kieb.repository.DesignationRepository;
import com.suffixit.kieb.service.DesignationService;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService {

    private final DesignationRepository designationRepository;

    private final CommitteeRepository committeeRepository;

    public DesignationServiceImpl(DesignationRepository designationRepository, CommitteeRepository committeeRepository) {
        this.designationRepository = designationRepository;
        this.committeeRepository = committeeRepository;
    }
    @Override
    public ResponseEntity<String> addDesignation(DesignationDTO designationDTO){

        CommitteeDesignation committeeDesignation = CommitteeDesignation.builder()
                .designationName(designationDTO.getDesignationName())
                .build();

        designationRepository.save(committeeDesignation);

        return ResponseEntity.ok("Committee designation added successfully!");

    }
    @Override
    public ResponseEntity<?> getAllDesignation(){

        List<CommitteeDesignation> committeeDesignationList = designationRepository.findAll();
        if (committeeDesignationList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No designations found");
        } else {
            return ResponseEntity.ok(committeeDesignationList);
        }
    }
}

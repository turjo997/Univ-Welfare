package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.entities.Division;
import com.suffixit.kieb.repository.DivisionRepository;
import com.suffixit.kieb.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionServiceImpl implements DivisionService {

    private final DivisionRepository divisionRepository;

    public DivisionServiceImpl(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    @Override
    public List<Division> getAll() {
        return divisionRepository.findAll(Sort.by("divisionName"));
    }

    @Override
    public Division get(Long divisionId) {
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);
        divisionOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Division is not found with Id: "+ divisionId));
        return divisionOptional.get();
    }
}

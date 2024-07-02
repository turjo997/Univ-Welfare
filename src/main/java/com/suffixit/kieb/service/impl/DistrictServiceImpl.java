package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.entities.District;
import com.suffixit.kieb.entities.Division;
import com.suffixit.kieb.repository.DistrictRepository;
import com.suffixit.kieb.service.DistrictService;
import com.suffixit.kieb.service.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final DivisionService divisionService;

    public DistrictServiceImpl(DistrictRepository districtRepository, DivisionService divisionService) {
        this.districtRepository = districtRepository;
        this.divisionService = divisionService;
    }


    @Override
    public District get(Long districtId) {
        Optional<District> districtOptional = districtRepository.findById(districtId);
        districtOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "District is not found with Id:"+districtId));
        return districtOptional.get();
    }

    @Override
    public List<District> getAll() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> getAllByDivision(Long divisionId) {
        Division division = divisionService.get(divisionId);
        return districtRepository.findAllByDivisionOrderByDistrictName(division);
    }
}

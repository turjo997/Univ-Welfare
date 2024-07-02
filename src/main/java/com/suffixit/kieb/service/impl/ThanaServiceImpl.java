package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.ThanaDTO;
import com.suffixit.kieb.entities.District;
import com.suffixit.kieb.entities.Thana;
import com.suffixit.kieb.repository.ThanaRepository;
import com.suffixit.kieb.service.DistrictService;
import com.suffixit.kieb.service.ThanaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class ThanaServiceImpl implements ThanaService {
    private final ThanaRepository thanaRepository;
    private final DistrictService districtService;

    public ThanaServiceImpl(ThanaRepository thanaRepository, DistrictService districtService) {
        this.thanaRepository = thanaRepository;
        this.districtService = districtService;
    }


    @Override
    public Thana get(Integer thanaId) {
        Optional<Thana> thanaOptional = thanaRepository.findById(thanaId);
        return thanaOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Thana is not found with Id:" + thanaId));
    }

    @Override
    public List<Thana> getAllThana() {
        return thanaRepository.findAll();
    }

    @Override
    public List<ThanaDTO> getAllThanaByDistrictId(Long districtId) {
        District district = districtService.get(districtId);

        List<Thana> thanas = thanaRepository.findAllByDistrictOrderByThanaName(district);

        List<ThanaDTO> thanaDTOs = thanas.stream().map(this::convertToDTO).collect(Collectors.toList());

        return thanaDTOs;
    }


    private ThanaDTO convertToDTO(Thana thana) {
        ThanaDTO thanaDTO = new ThanaDTO();
        thanaDTO.setId(thana.getId());
        thanaDTO.setThanaName(thana.getThanaName());
        return thanaDTO;
    }
}

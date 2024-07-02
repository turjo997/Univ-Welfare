package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.ThanaDTO;
import com.suffixit.kieb.entities.District;
import com.suffixit.kieb.entities.Division;
import com.suffixit.kieb.entities.Thana;
import com.suffixit.kieb.service.DistrictService;
import com.suffixit.kieb.service.DivisionService;
import com.suffixit.kieb.service.ThanaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
public class AddressController {

    private final ThanaService thanaService;
    private final DistrictService districtService;
    private final DivisionService divisionService;

    public AddressController(ThanaService thanaService, DistrictService districtService, DivisionService divisionService) {
        this.thanaService = thanaService;
        this.districtService = districtService;
        this.divisionService = divisionService;
    }

    @GetMapping("/thana")
    public List<Thana> getAllThana() {
        return thanaService.getAllThana();
    }

    @GetMapping("/thana-by-district")
    public List<ThanaDTO> getAllThanaByDistrictId(@RequestParam Long districtId) {
        return thanaService.getAllThanaByDistrictId(districtId);
    }

    @GetMapping("/district")
    public List<District> getAllDistrict() {
        return districtService.getAll();
    }

    @GetMapping("/district-by-division")
    public List<District> getAllDistrictByDivision(@RequestParam Long divisionId) {
        return districtService.getAllByDivision(divisionId);
    }

    @GetMapping("/division")
    public List<Division> getAllDivision() {
        return divisionService.getAll();
    }

}

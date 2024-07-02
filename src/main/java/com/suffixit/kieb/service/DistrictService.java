package com.suffixit.kieb.service;

import com.suffixit.kieb.entities.District;

import java.util.List;
public interface DistrictService {
    District get(Long districtId);
    List<District> getAll();
    List<District> getAllByDivision(Long divisionId);
}

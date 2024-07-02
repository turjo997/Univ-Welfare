package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.ThanaDTO;
import com.suffixit.kieb.entities.Thana;

import java.util.List;
public interface ThanaService {
    Thana get(Integer thanaId);
    List<Thana> getAllThana();
    List<ThanaDTO> getAllThanaByDistrictId(Long districtId);
}

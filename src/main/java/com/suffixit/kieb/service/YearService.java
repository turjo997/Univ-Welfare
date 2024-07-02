package com.suffixit.kieb.service;

import com.suffixit.kieb.entities.Year;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface YearService {

    List<Year> getAll();

    Year get(Integer yearId);
}

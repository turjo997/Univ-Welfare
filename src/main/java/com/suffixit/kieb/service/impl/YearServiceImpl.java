package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.entities.Year;
import com.suffixit.kieb.repository.YearRepository;
import com.suffixit.kieb.service.YearService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class YearServiceImpl implements YearService {

    private final YearRepository yearRepository;

    public YearServiceImpl(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }


    @Override
    public List<Year> getAll() {
        return yearRepository.findAll(Sort.by("id"));
    }

    @Override
    public Year get(Integer yearId) {
        Optional<Year> year = yearRepository.findById(yearId);
        year.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Year is not found with Id: "+ yearId));
        return year.get();
    }
}

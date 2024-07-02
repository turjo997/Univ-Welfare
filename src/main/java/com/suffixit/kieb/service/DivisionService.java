package com.suffixit.kieb.service;

import com.suffixit.kieb.entities.Division;

import java.util.List;

public interface DivisionService {

    List<Division> getAll();
    Division get(Long division);

}

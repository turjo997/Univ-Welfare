package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepository extends JpaRepository<Degree, Integer> {

    Degree findDegreeById(Integer degreeId);
}

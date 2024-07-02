package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Year;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends JpaRepository<Year, Integer> {

    Year findByYear(String fullYear);

}

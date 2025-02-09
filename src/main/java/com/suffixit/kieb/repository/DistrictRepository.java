package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.District;
import com.suffixit.kieb.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findAllByDivisionOrderByDistrictName(Division division);

    District findDistrictById(Integer districtId);
}

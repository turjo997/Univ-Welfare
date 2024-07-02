package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.District;
import com.suffixit.kieb.entities.Thana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanaRepository extends JpaRepository<Thana, Integer> {

    List<Thana> findAllByDistrictOrderByThanaName(District district);


}

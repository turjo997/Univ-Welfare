package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.CommitteeDesignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<CommitteeDesignation, Integer> {


}

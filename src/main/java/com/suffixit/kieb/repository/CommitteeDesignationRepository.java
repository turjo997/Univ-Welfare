package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.CommitteeDesignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommitteeDesignationRepository extends JpaRepository<CommitteeDesignation, Integer> {

    Optional<CommitteeDesignation> findCommitteeDesignationById(Integer committeeDesignationId);
}

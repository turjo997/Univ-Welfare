package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.CommitteeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommitteeDepartmentRepository extends JpaRepository<CommitteeDepartment, Integer> {

    Optional<CommitteeDepartment> findCommitteeDepartmentById(Integer committeeDepartmentId);
}

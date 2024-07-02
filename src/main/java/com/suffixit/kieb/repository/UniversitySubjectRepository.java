package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.UniversitySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversitySubjectRepository extends JpaRepository<UniversitySubject, Integer> {


    UniversitySubject findBySubjectCode(String batchCode);
}

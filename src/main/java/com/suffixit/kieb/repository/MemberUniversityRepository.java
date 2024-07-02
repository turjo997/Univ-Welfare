package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.UniversitySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberUniversityRepository extends JpaRepository<UniversitySubject, Integer> {
}

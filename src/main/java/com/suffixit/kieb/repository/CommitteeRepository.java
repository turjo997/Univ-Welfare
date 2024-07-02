package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Integer> {

    Optional<Committee> findCommitteeById(Integer id);

    List<Committee> findByStatus(Byte inActiveStatus);

}

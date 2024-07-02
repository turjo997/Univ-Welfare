package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center , Integer> {
}

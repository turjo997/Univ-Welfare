package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.ResultType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultTypeRepository extends JpaRepository<ResultType, Integer> {
    ResultType findResultTypeById(Integer resultTypeId);
}

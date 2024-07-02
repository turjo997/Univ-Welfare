package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , Integer> {

    boolean existsByDepartmentCodeOrDepartmentName(String departmentCode, String departmentName);


    Department findByDepartmentCode(String departmentCode);

}

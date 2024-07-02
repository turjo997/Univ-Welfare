package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.MemberDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDivisionRepository extends JpaRepository<MemberDivision, Integer > {

    MemberDivision findMemberDivisionById(Integer memberDivisionId);

    MemberDivision findByMemDivisionCode(String memDivisionCode);
}

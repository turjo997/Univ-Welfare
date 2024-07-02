package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.MemberSubjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSubjectRepository extends JpaRepository<MemberSubjectInfo, Integer> {

}

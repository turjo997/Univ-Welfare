package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberEducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberEducationInfoRepository extends JpaRepository<MemberEducationInfo, Integer> {

    List<MemberEducationInfo> findByMember(Member member);

    //List<MemberEducationInfo> findMemberById(Integer memberId);

    List<MemberEducationInfo> findByMemberId(Integer memberId);

    long countByMemberId(Integer memberId);
}


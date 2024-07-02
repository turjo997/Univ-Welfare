package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberProfessionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberProfessionalInfoRepository extends JpaRepository<MemberProfessionalInfo, Integer> {
    Optional<MemberProfessionalInfo> findByMember(Member member);

    List<MemberProfessionalInfo> findByMemberId(Integer memberId);
}

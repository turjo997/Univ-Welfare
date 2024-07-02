package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberPublicationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPublicationInfoRepository extends JpaRepository<MemberPublicationInfo, Integer> {

    List<MemberPublicationInfo> findByMemberId(Integer memberId);

    Member findMemberById(Integer memberId);

    List<MemberPublicationInfo> findByMember(Member member);
}

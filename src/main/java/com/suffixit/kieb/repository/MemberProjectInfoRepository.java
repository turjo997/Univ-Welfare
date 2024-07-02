package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberProjectInfoRepository extends JpaRepository<MemberProjectInfo,Integer> {

    Optional<MemberProjectInfo> findByMember(Member member);

    List<MemberProjectInfo> findByMemberId(Integer memberId);
}

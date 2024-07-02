package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddress, Integer> {

    MemberAddress findMemberAddressById(Integer memberAddressId);

    @Query("from MemberAddress where member=?1 and now() between ed and td")
    List<MemberAddress> findByMember(Member member);

    @Query("from MemberAddress where member.id=?1 and now() between ed and td")
    List<MemberAddress> findByMemberId(Integer memberId);

    boolean existsByMember(Member member);

}

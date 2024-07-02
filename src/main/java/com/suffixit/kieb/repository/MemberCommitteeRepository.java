package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Committee;
import com.suffixit.kieb.entities.CommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberCommitteeRepository extends JpaRepository<CommitteeMember, Integer> {

    Optional<CommitteeMember> findMemberCommitteeById(Integer committeeMemberId);

    List<CommitteeMember> findByCommittee(Committee committee);
}

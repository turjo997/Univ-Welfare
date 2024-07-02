package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserName(String userName);

    Users findByEmail(String email);

    Optional<Users> findByMemberId(Integer memberId);

    Optional<Users> findByMember(Member member);

    Optional<Users> findByMember(Optional<Member> existingMember);

     void deleteByMemberId( Integer memberId);
}

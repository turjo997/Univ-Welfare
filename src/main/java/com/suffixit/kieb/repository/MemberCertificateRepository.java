package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.MemberCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCertificateRepository extends JpaRepository<MemberCertificate, Integer> {

    List<MemberCertificate> findByMemberId(Integer memberId);

    long countByMemberId(Integer memberId);

}

package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.MemberSocialLink;
import com.suffixit.kieb.entities.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSocialLinkRepository extends JpaRepository<MemberSocialLink, Integer> {

    List<MemberSocialLink> findByMemberId(Integer memberId);
}

package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLinkRepository extends JpaRepository<SocialLink, Integer> {
    SocialLink findSocialLinkById(Integer linkTypeId);

}

package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.MemberSocialLinkDTO;
import com.suffixit.kieb.dto.MemberSocialLinkListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MemberSocialLinkService {

    ResponseEntity<String> addMemberSocialLink(MemberSocialLinkListDTO memberSocialLinkListDTO);
    ResponseEntity<String> modifyMemberSocialLink(MemberSocialLinkListDTO memberSocialLinkListDTO);

}

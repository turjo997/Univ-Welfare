package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.SocialLinkDTO;
import com.suffixit.kieb.dto.SocialLinkProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface SocialLinkService {
    ResponseEntity<String> addSocialLinkService(SocialLinkDTO socialLinkDTO);

    List<SocialLinkProfileDTO> getAllSocialLink();

}

package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.SocialLinkDTO;
import com.suffixit.kieb.dto.SocialLinkProfileDTO;
import com.suffixit.kieb.entities.SocialLink;
import com.suffixit.kieb.repository.SocialLinkRepository;
import com.suffixit.kieb.service.SocialLinkService;
import com.suffixit.kieb.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class SocialLinkServiceImpl implements SocialLinkService {

    private final SocialLinkRepository socialLinkRepository;

    private final String uploadDir;

    SocialLinkServiceImpl(SocialLinkRepository socialLinkRepository, @Value("${fileStore.directory}") String uploadDir) {
        this.socialLinkRepository = socialLinkRepository;
        this.uploadDir = uploadDir;
    }

    @Override
    public ResponseEntity<String> addSocialLinkService(SocialLinkDTO socialLinkDTO) {

        SocialLink socialLink = SocialLink.builder().socialName(socialLinkDTO.getSocialName()).build();

        if (socialLinkDTO.getSocialIcon() != null && !socialLinkDTO.getSocialIcon().isEmpty()) {
            try {
                String fileName = socialLink.getId() + "_" + socialLinkDTO.getSocialIcon().getOriginalFilename();
                FileUploadUtil.saveFile(uploadDir, fileName, socialLinkDTO.getSocialIcon());

                socialLink.setSocialIcon(fileName);

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload picture.");
            }
        }

        socialLinkRepository.save(socialLink);

        return ResponseEntity.ok("Social Link created successfully!");
    }

    @Override
    public List<SocialLinkProfileDTO> getAllSocialLink() {
        List<SocialLink> socialLinkInfos = socialLinkRepository.findAll();

        if (socialLinkInfos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No social links found");
        }

        return socialLinkInfos.stream().map(socialLink -> SocialLinkProfileDTO.builder().id(socialLink.getId()).linkName(socialLink.getSocialName()).build()).collect(Collectors.toList());
    }


}

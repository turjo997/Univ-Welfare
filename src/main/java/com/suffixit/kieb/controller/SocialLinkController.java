package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.SocialLinkDTO;
import com.suffixit.kieb.dto.SocialLinkProfileDTO;
import com.suffixit.kieb.service.SocialLinkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/socialLink")
public class SocialLinkController {

    private final SocialLinkService socialLinkService;

    public SocialLinkController(SocialLinkService socialLinkService){
        this.socialLinkService = socialLinkService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSocialLink(@Valid @RequestBody SocialLinkDTO socialLinkDTO){
        return socialLinkService.addSocialLinkService(socialLinkDTO);
    }

    @GetMapping("/all")
    public List<SocialLinkProfileDTO> getAllSocialLink() {
        return socialLinkService.getAllSocialLink();
    }

}

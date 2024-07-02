package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.MemberSocialLinkListDTO;
import com.suffixit.kieb.service.MemberSocialLinkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/memberSocialLink")

public class MemberSocialLinkController {

    private final MemberSocialLinkService memberSocialLinkService;

    public MemberSocialLinkController(MemberSocialLinkService memberSocialLinkService){
        this.memberSocialLinkService = memberSocialLinkService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addMemberSocialLink(@Valid @RequestBody MemberSocialLinkListDTO memberSocialLinkListDTO){
        return memberSocialLinkService.addMemberSocialLink(memberSocialLinkListDTO);
    }
    @PutMapping("/modify")
    public ResponseEntity<String> modifyMemberSocialLink(@Valid @RequestBody MemberSocialLinkListDTO memberSocialLinkListDTO){
        return memberSocialLinkService.modifyMemberSocialLink(memberSocialLinkListDTO);
    }

}

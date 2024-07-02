package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.MemberCertificateInfoListDTO;
import com.suffixit.kieb.service.MemberCertificateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/memberCertificate")
public class MemberCertificateController {

    private final MemberCertificateService memberCertificateService;

    public MemberCertificateController(MemberCertificateService memberCertificateService) {
        this.memberCertificateService = memberCertificateService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMemberCertificate(@Valid MemberCertificateInfoListDTO memberCertificateInfoListDTO) {
        return memberCertificateService.addMemberCertificate(memberCertificateInfoListDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyMemberCertificate(@Valid MemberCertificateInfoListDTO memberCertificateInfoListDTO) {
        return memberCertificateService.modifyMemberCertificate(memberCertificateInfoListDTO);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> memberCertificateProfile(@PathVariable("id") Integer id) {
        return memberCertificateService.getAMemberCertificateForAMember(id);
    }

}

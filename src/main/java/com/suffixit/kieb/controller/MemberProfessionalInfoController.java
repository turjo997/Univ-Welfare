package com.suffixit.kieb.controller;



import com.suffixit.kieb.dto.MemberProfessionalInfoListDTO;
import com.suffixit.kieb.service.MemberProfessionalInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/memberProfession")
public class MemberProfessionalInfoController {

    private final MemberProfessionalInfoService memberProfessionalInfoService;

    public MemberProfessionalInfoController(MemberProfessionalInfoService memberProfessionalInfoService) {
        this.memberProfessionalInfoService = memberProfessionalInfoService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> memberProfessionalInfo(@Valid @RequestBody MemberProfessionalInfoListDTO memberProfessionalInfoListDTO){
        return memberProfessionalInfoService.addMemberProfessionalInfo(memberProfessionalInfoListDTO);
    }

    @PutMapping("/profession/update")
    public ResponseEntity<String> updateProfession(@Valid @RequestBody MemberProfessionalInfoListDTO memberProfessionalInfoListDTO){
        return memberProfessionalInfoService.updateProfession(memberProfessionalInfoListDTO);
    }


}

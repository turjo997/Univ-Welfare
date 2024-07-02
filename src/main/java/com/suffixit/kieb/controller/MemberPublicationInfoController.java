package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.MemberPublicationListDTO;
import com.suffixit.kieb.service.MemberPublicationInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/memberPublicationInfo")
public class MemberPublicationInfoController {

    private final MemberPublicationInfoService memberPublicationInfoService;

    public MemberPublicationInfoController(MemberPublicationInfoService memberPublicationInfoService) {
        this.memberPublicationInfoService = memberPublicationInfoService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addMemberPublicationInfo(@Valid @RequestBody MemberPublicationListDTO memberPublicationListDTO){
        return memberPublicationInfoService.addMemberPublicationInfo(memberPublicationListDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> updateMemberPublicationInfo(@Valid @RequestBody MemberPublicationListDTO memberPublicationListDTO){

        return memberPublicationInfoService.updatePublication(memberPublicationListDTO);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deletePublicationsByMemberId(@PathVariable("id") Integer memberId){
        return memberPublicationInfoService.deletePublicationsByMemberId(memberId);
    }

}

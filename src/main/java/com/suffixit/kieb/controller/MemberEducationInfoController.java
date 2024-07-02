package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.MemberEducationInfoListDTO;
import com.suffixit.kieb.entities.MemberEducationInfo;
import com.suffixit.kieb.service.MemberEductionInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/memberEducationInfo")
public class MemberEducationInfoController {

    private final MemberEductionInfoService memberEductionInfoService;

    public MemberEducationInfoController(MemberEductionInfoService memberEductionInfoService){
        this.memberEductionInfoService = memberEductionInfoService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addMemberEducationInfo(@Valid @RequestBody MemberEducationInfoListDTO memberEducationInfoListDTO){

        return memberEductionInfoService.addMemberEducationInfo(memberEducationInfoListDTO);
    }
    @PutMapping("/modify")
    public ResponseEntity<String> modifyMemberEducationInfo(@Valid @RequestBody MemberEducationInfoListDTO memberEducationInfoListDTO){
        return memberEductionInfoService.modifyMemberEducationInfo(memberEducationInfoListDTO);
    }

    @GetMapping("/all/{memberId}")
    public ResponseEntity<MemberEducationInfo> getMemberEducationByMemberId(@PathVariable("memberId") Integer memberId){
        return (ResponseEntity<MemberEducationInfo>) memberEductionInfoService.getAllEducationInfoForMember(memberId);

    }


}

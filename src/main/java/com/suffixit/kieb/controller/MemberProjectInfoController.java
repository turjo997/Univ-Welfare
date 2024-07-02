package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.MemberProjectInfoListDTO;
import com.suffixit.kieb.service.MemberProjectInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/memberProjectInfo")
public class MemberProjectInfoController {

    private final MemberProjectInfoService memberProjectInfoService;

    public MemberProjectInfoController(MemberProjectInfoService memberProjectInfoService) {
        this.memberProjectInfoService = memberProjectInfoService;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addProjectInfo(@Valid @RequestBody MemberProjectInfoListDTO memberProjectInfoListDTO){
        return memberProjectInfoService.addProjectInfo(memberProjectInfoListDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> updateProject(@Valid @RequestBody MemberProjectInfoListDTO memberProjectInfoListDTO){
        return memberProjectInfoService.updateProject(memberProjectInfoListDTO);
    }

}

package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.MemberDivisionDTO;
import com.suffixit.kieb.entities.MemberDivision;
import com.suffixit.kieb.service.MemberDivisionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/memberDivision")
public class MemberDivisionController {

    private final MemberDivisionService memberDivisionService;

    public MemberDivisionController(MemberDivisionService memberDivisionService) {
        this.memberDivisionService = memberDivisionService;
    }

    @GetMapping("/all")
    public List<MemberDivision> getAllMemberDivision() {

        return memberDivisionService.getAll();

    }

    @GetMapping("/division/all")
    public List<MemberDivisionDTO> getAllDivision() {
        return memberDivisionService.getAllDivision();
    }
}

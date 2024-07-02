package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.CommitteeDTO;
import com.suffixit.kieb.dto.CommitteeMemberDTO;
import com.suffixit.kieb.service.CommitteeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/memberCommittee")
public class MemberCommitteeController {

    private final CommitteeService committeeService;

    public MemberCommitteeController(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addCommitteeMember(@Valid @RequestBody CommitteeDTO committeeDTO){

        return committeeService.addCommittee(committeeDTO);
    }
    @PostMapping("/designation/member/add")
    public ResponseEntity<String> addDesignationAndMemberInExistingCommittee(@RequestBody CommitteeMemberDTO committeeMemberDTO){
        return committeeService.addMemberAndDesignationInCommittee(committeeMemberDTO);
    }
    @PutMapping("/committee/status")
    public ResponseEntity<String> updateStatus(@RequestBody CommitteeMemberDTO committeeMemberDTO){
        return committeeService.updateCommitteeWithStatus(committeeMemberDTO);
    }
    @DeleteMapping("/committeeMember/delete/{id}")
    ResponseEntity<String> deleteMembersByCommitteeId(@PathVariable("id") Integer committeeId){
        return committeeService.deleteMembersByCommitteeId(committeeId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCommittee(){
        return committeeService.getAllCommittee();
    }

}

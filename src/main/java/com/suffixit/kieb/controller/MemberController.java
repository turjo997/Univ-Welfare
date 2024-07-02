package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.*;
import com.suffixit.kieb.service.AuthService;
import com.suffixit.kieb.service.MemberService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    public MemberController(MemberService memberService, AuthService authService){
        this.memberService= memberService;
        this.authService = authService;
    }

    @PutMapping("/update-password")
    public boolean updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        //return ResponseEntity.ok(authService.updatePassword(updatePasswordRequest));
        return authService.updatePassword(updatePasswordRequest);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<String> modifyMember(@PathVariable("id") Integer id,
                                               @Valid @RequestBody MemberDTO memberDTO){
        return memberService.modifyMember(id, memberDTO);
    }
//
//    @GetMapping("/getYear/{year}")
//    public ResponseEntity<?> getMemberStartingYear(@PathVariable("year") String rollNo){
//        return memberService.getMemberDivisionNameAndYear(rollNo);
//    }

    @GetMapping("/picture/{memberId}")
    public ResponseEntity<InputStreamResource> getMemberPicture(@PathVariable("memberId") Integer memberId){
        return memberService.getMemberPicture(memberId);
    }


    @PutMapping("/add/picture")
    public ResponseEntity<String> addPicture(@Valid @ModelAttribute MemberProfileImageDTO memberProfileImageDTO ) throws Exception{
        return memberService.addPicture(memberProfileImageDTO);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> userProfile(@PathVariable("id") Integer id){
        return memberService.memberProfile(id);
    }

    @GetMapping("/allProfile/{memberId}")
    public ResponseEntity<?> memberAllProfile(@PathVariable("memberId") Integer memberId){
        return memberService.getMemberAllProfile(memberId);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteMemberById(@PathVariable("id")  Integer id){
        return memberService.deleteMemberById(id);
    }

//    @GetMapping("/member-statistics/month")
//    public List<MemberStatisticDTO> getMemberStatisticsForMonth(
//            @RequestParam(name = "fromDate") String fromDate, @RequestParam(name = "toDate") String toDate
//    ) {
//
//        LocalDate fDate = LocalDate.parse(fromDate);
//        LocalDate tDate = LocalDate.parse(toDate);
//        return memberService.getMemberStatisticsForMonth(fDate, tDate);
//    }



}

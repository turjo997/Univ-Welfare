package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.MemberRegisteredDTO;
import com.suffixit.kieb.model.LoginRequest;
import com.suffixit.kieb.model.LoginResponse;
import com.suffixit.kieb.service.AuthService;
import com.suffixit.kieb.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody LoginRequest loginRequest)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        return authService.signIn(loginRequest);
    }

    @GetMapping("/getBatchNameAndYear/{rollNo}")
    public ResponseEntity<?> getMemberDivisionName(@PathVariable("rollNo") String rollNo){
        return memberService.getMemberDivisionNameAndYear(rollNo);
    }

    @PostMapping("/member/register")
    public ResponseEntity<String> registeredMember(@Valid @RequestBody MemberRegisteredDTO memberRegisteredDTO){
        return memberService.registerMember(memberRegisteredDTO);
    }

}

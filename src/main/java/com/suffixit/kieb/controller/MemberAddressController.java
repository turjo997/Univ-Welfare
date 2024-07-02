package com.suffixit.kieb.controller;

import com.suffixit.kieb.dto.MemberAddressDTO;
import com.suffixit.kieb.service.MemberAddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/member")
public class MemberAddressController {

    private final MemberAddressService memberAddressService;

    public MemberAddressController(MemberAddressService memberAddressService){
        this.memberAddressService = memberAddressService;
    }


    @PostMapping("/address")
    public ResponseEntity<String> memberAddress(@Valid @RequestBody List<MemberAddressDTO> memberAddressDTO){
        return memberAddressService.addMemberAddresses(memberAddressDTO);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddress(@PathVariable("id") Integer id){
        return memberAddressService.getAddressById(id);
    }

    @PutMapping("/address/modify")
    public ResponseEntity<?> updateAddress(@Valid @RequestBody List<MemberAddressDTO> memberAddressDTO){
        return memberAddressService.updateAddress(memberAddressDTO);
    }

}

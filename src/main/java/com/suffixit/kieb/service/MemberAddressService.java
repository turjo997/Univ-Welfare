package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.MemberAddressDTO;
import com.suffixit.kieb.entities.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberAddressService {
    ResponseEntity<String> addMemberAddresses(List<MemberAddressDTO>  memberAddressDTO);

    ResponseEntity<?> getAddressById(Integer id);


    ResponseEntity<?> updateAddress(List<MemberAddressDTO> memberAddressDTO);

}

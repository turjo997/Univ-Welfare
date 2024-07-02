package com.suffixit.kieb.service;


import com.suffixit.kieb.dto.MemberProfessionalInfoListDTO;
import org.springframework.http.ResponseEntity;

public interface MemberProfessionalInfoService {

    ResponseEntity<String> addMemberProfessionalInfo(MemberProfessionalInfoListDTO memberProfessionalInfoListDTO);

    ResponseEntity<String> updateProfession(MemberProfessionalInfoListDTO memberProfessionalInfoListDTO);
}


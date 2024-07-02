package com.suffixit.kieb.service;


import com.suffixit.kieb.dto.MemberCertificateInfoListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MemberCertificateService {

    ResponseEntity<String> addMemberCertificate(MemberCertificateInfoListDTO memberCertificateInfoListDTO);
    ResponseEntity<String> modifyMemberCertificate(MemberCertificateInfoListDTO memberCertificateInfoListDTO);
    ResponseEntity<?> getAMemberCertificateForAMember(Integer id);

}

package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.MemberPublicationInfoDTO;
import com.suffixit.kieb.dto.MemberPublicationListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MemberPublicationInfoService {

    //ResponseEntity<String> AddMemberPublicationInfo(MemberPublicationInfoDTO memberPublicationInfoDTO);

    ResponseEntity<String> addMemberPublicationInfo(MemberPublicationListDTO memberPublicationListDTO);

    ResponseEntity<String> updatePublication(MemberPublicationListDTO memberPublicationListDTO);

    ResponseEntity<String> deletePublicationsByMemberId(Integer memberId);

    //ResponseEntity<String> modifyMemberPublicationInfo(Integer id, MemberPublicationInfoDTO memberPublicationInfoDTO);

    //ResponseEntity<?> getAPublicationInfoForAMember(Integer id);
}

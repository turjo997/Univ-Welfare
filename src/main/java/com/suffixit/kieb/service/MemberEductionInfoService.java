package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.MemberEducationInfoDTO;
import com.suffixit.kieb.dto.MemberEducationInfoListDTO;
import com.suffixit.kieb.entities.MemberEducationInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MemberEductionInfoService {

    ResponseEntity<String> addMemberEducationInfo(MemberEducationInfoListDTO memberEducationInfoListDTO);
    ResponseEntity<String> modifyMemberEducationInfo(MemberEducationInfoListDTO memberEducationInfoListDTO);

    List<MemberEducationInfo> getAllEducationInfoForMember(Integer memberId);
}

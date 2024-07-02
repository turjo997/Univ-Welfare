package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.*;
import com.suffixit.kieb.enumerations.ApproveStatus;
import jakarta.transaction.Transactional;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public interface MemberService {

    ResponseEntity<String> registerMember(MemberRegisteredDTO memberRegisteredDTO);

    ResponseEntity<String> modifyMember(Integer id,MemberDTO memberDTO) ;

    ResponseEntity<?> memberProfile(Integer id);

//    ResponseEntity<?> getMemberDivisionName(String memDivisionCode);

//    ResponseEntity<?> getMemberStartingYear(String year);
//
    ResponseEntity<?> getMemberAllProfile(Integer memberId);

    ResponseEntity<InputStreamResource> getMemberPicture(Integer memberId);

    ResponseEntity<?> getMemberDivisionNameAndYear(String rollNumber);

    ResponseEntity<?> deleteMemberById(Integer id);

    ResponseEntity<String> addPicture(MemberProfileImageDTO memberProfileImageDTO) throws IOException;

    //Map<ApproveStatus, Long> calculateLastMonthData();

    List<MemberStatisticDTO> getMemberStatisticsForMonth();
}

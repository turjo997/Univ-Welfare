package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.CommitteeDTO;
import com.suffixit.kieb.dto.CommitteeMemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CommitteeService {

    ResponseEntity<String> addCommittee(CommitteeDTO committeeDTO);

    ResponseEntity<String> updateCommitteeWithStatus(CommitteeMemberDTO committeeMemberDTO);
    ResponseEntity<String> addMemberAndDesignationInCommittee(CommitteeMemberDTO committeeMemberDTO);

    ResponseEntity<?> getAllCommittee();

    ResponseEntity<?> getAllMemberCommittee();

    ResponseEntity<String> deleteMembersByCommitteeId(Integer committeeId);

}

package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.MemberCommitteeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MemberCommitteeService {

    ResponseEntity<String> addMemberCommittee(MemberCommitteeDTO memberCommitteeDTO);
}

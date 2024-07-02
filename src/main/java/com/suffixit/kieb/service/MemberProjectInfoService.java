package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.MemberProjectInfoDTO;
import com.suffixit.kieb.dto.MemberProjectInfoListDTO;
import org.springframework.http.ResponseEntity;

public interface MemberProjectInfoService {
    ResponseEntity<String> addProjectInfo(MemberProjectInfoListDTO memberProjectInfoDTO);

    ResponseEntity<?> updateProject(MemberProjectInfoListDTO memberProjectInfoListDTO);

}

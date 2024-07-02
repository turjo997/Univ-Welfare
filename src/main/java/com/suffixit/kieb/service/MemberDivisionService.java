package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.MemberDivisionDTO;
import com.suffixit.kieb.entities.MemberDivision;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MemberDivisionService {

    List<MemberDivision> getAll();

    MemberDivision get(Integer memberDivisionId);

    List<MemberDivisionDTO> getAllDivision();

}

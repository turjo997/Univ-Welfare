package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.MemberDivisionDTO;
import com.suffixit.kieb.entities.MemberDivision;
import com.suffixit.kieb.repository.MemberDivisionRepository;
import com.suffixit.kieb.service.MemberDivisionService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberDivisionServiceImpl implements MemberDivisionService {

    private final MemberDivisionRepository memberDivisionRepository;

    public MemberDivisionServiceImpl(MemberDivisionRepository memberDivisionRepository) {
        this.memberDivisionRepository = memberDivisionRepository;
    }


    @Override
    public List<MemberDivision> getAll() {
        return memberDivisionRepository.findAll(Sort.by("fullName"));
    }

    @Override
    public MemberDivision get(Integer memberDivisionId) {
        Optional<MemberDivision> memberDivision = memberDivisionRepository.findById(memberDivisionId);
        memberDivision.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member Division is not found with Id: "+ memberDivisionId));
        return memberDivision.get();
    }

    @Override
    public List<MemberDivisionDTO> getAllDivision() {
        List<MemberDivision> memberDivisionInfos = memberDivisionRepository.findAll();

        if (memberDivisionInfos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "No member divisions found");
        }

        return memberDivisionInfos.stream()
                .map(division -> MemberDivisionDTO.builder()
                        .id(division.getId())
                        .divisionName(division.getFullName())
                        .build())
                .collect(Collectors.toList());

    }
}

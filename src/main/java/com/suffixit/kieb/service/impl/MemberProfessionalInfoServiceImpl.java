package com.suffixit.kieb.service.impl;


import com.suffixit.kieb.dto.MemberProfessionalInfoDTO;
import com.suffixit.kieb.dto.MemberProfessionalInfoListDTO;
import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberEducationInfo;
import com.suffixit.kieb.entities.MemberProfessionalInfo;
import com.suffixit.kieb.entities.MemberProjectInfo;
import com.suffixit.kieb.repository.MemberProfessionalInfoRepository;
import com.suffixit.kieb.repository.MemberRepository;
import com.suffixit.kieb.service.MemberProfessionalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class MemberProfessionalInfoServiceImpl implements MemberProfessionalInfoService {

    private final MemberRepository memberRepository;
    private final MemberProfessionalInfoRepository memberProfessionalInfoRepository;

    public MemberProfessionalInfoServiceImpl(MemberRepository memberRepository, MemberProfessionalInfoRepository memberProfessionalInfoRepository) {
        this.memberRepository = memberRepository;
        this.memberProfessionalInfoRepository = memberProfessionalInfoRepository;
    }

    @Override
    public ResponseEntity<String> addMemberProfessionalInfo(MemberProfessionalInfoListDTO memberProfessionalInfoListDTO) {

        Integer memberId = memberProfessionalInfoListDTO.getMemberId();

        Member member = memberRepository.findMemberById(memberId);

        if(member == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with this id is not found!");
        }

        for(MemberProfessionalInfoDTO memberProfessionalInfoDTO : memberProfessionalInfoListDTO.getMemberProfessionalInfoDTOList()){

            MemberProfessionalInfo memberProfessionalInfo = new MemberProfessionalInfo();

            memberProfessionalInfo.setCompanyAddress(memberProfessionalInfoDTO.getCompanyAddress());
            memberProfessionalInfo.setCompanyName(memberProfessionalInfoDTO.getCompanyName());
            memberProfessionalInfo.setCompanyType(memberProfessionalInfoDTO.getCompanyType());
            memberProfessionalInfo.setDesignation(memberProfessionalInfoDTO.getDesignation());
            memberProfessionalInfo.setFromDate(memberProfessionalInfoDTO.getFromDate());
            memberProfessionalInfo.setTillDate(memberProfessionalInfoDTO.getIsPresent() ? null : memberProfessionalInfoDTO.getTillDate());
            memberProfessionalInfo.setIsPresentJob(memberProfessionalInfoDTO.getIsPresent());
            memberProfessionalInfo.setJd(memberProfessionalInfoDTO.getJd());
            memberProfessionalInfo.setMember(member);

            memberProfessionalInfoRepository.save(memberProfessionalInfo);

        }

        return ResponseEntity.ok("Member Professional Info created successfully!");
    }

    @Override
    public ResponseEntity<String> updateProfession(MemberProfessionalInfoListDTO memberProfessionalInfoListDTO) {


        Integer memberId = memberProfessionalInfoListDTO.getMemberId();

        List<MemberProfessionalInfo> member = memberProfessionalInfoRepository.findByMemberId(memberId);

        Member memberobj = memberRepository.findMemberById(memberId);

        if(member.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Member with this id is not found!");
        }

        Set<Integer> memberProfessionalList = memberProfessionalInfoListDTO.getMemberProfessionalInfoDTOList().stream().map(MemberProfessionalInfoDTO::getId).collect(Collectors.toSet());

        Map<Integer, MemberProfessionalInfo> professionalIdMap = memberProfessionalInfoRepository.findAllById(memberProfessionalList).stream()
                .collect(Collectors.toMap(MemberProfessionalInfo::getId, memberProfessionalInfo -> memberProfessionalInfo, (pre, post) -> pre));

        for(MemberProfessionalInfoDTO memberProfessionalInfoDTO: memberProfessionalInfoListDTO.getMemberProfessionalInfoDTOList()){

            Optional<MemberProfessionalInfo> existingMemberProfessional = Optional.ofNullable(professionalIdMap.get(memberProfessionalInfoDTO.getId()));

            if(existingMemberProfessional.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Member Professional with this id not found!");
            }

//            MemberProfessionalInfo existingMemberProfessionInfo = professionalIdMap.get(memberProfessionalInfoDTO.getId());
//
//            if (existingMemberProfessionInfo == null || !existingMemberProfessionInfo.getMember().getId().equals(memberObj.getId())) {
//                throw new BadCredentialsException("Member Project Info with this id not found for the specified member!");
//            }
//


            MemberProfessionalInfo memberProfessionalInfo = existingMemberProfessional.get();

            memberProfessionalInfo.setCompanyName(memberProfessionalInfoDTO.getCompanyName());
            memberProfessionalInfo.setCompanyType(memberProfessionalInfoDTO.getCompanyType());
            memberProfessionalInfo.setCompanyAddress(memberProfessionalInfoDTO.getCompanyAddress());
            memberProfessionalInfo.setDesignation(memberProfessionalInfoDTO.getDesignation());
            memberProfessionalInfo.setFromDate(memberProfessionalInfoDTO.getFromDate());
            memberProfessionalInfo.setJd(memberProfessionalInfoDTO.getJd());
            memberProfessionalInfo.setTillDate(memberProfessionalInfoDTO.getIsPresent() ? null : memberProfessionalInfoDTO.getTillDate());
            memberProfessionalInfo.setIsPresentJob(memberProfessionalInfoDTO.getIsPresent());
            memberProfessionalInfo.setMember(memberobj);

            memberProfessionalInfoRepository.save(memberProfessionalInfo);

        }

        return ResponseEntity.ok("Member Updated Successfully!");
    }

}






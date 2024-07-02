package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.MemberProjectInfoDTO;
import com.suffixit.kieb.dto.MemberProjectInfoListDTO;
import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberProfessionalInfo;
import com.suffixit.kieb.entities.MemberProjectInfo;
import com.suffixit.kieb.repository.MemberProjectInfoRepository;
import com.suffixit.kieb.repository.MemberRepository;
import com.suffixit.kieb.service.MemberProjectInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class MemberProjectInfoServiceImpl implements MemberProjectInfoService {

    private final MemberRepository memberRepository;
    private final MemberProjectInfoRepository memberProjectInfoRepository;

    public MemberProjectInfoServiceImpl(MemberRepository memberRepository, MemberProjectInfoRepository memberProjectInfoRepository) {
        this.memberRepository = memberRepository;
        this.memberProjectInfoRepository = memberProjectInfoRepository;
    }

    @Override
    public ResponseEntity<String> addProjectInfo(MemberProjectInfoListDTO memberProjectInfoDTO) {
        Optional<Member> existingMember = memberRepository.findById(memberProjectInfoDTO.getMemberId());

        if(existingMember.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with this id not found!");
        }

        Member member = existingMember.get();

        for(MemberProjectInfoDTO memberProjectDTO: memberProjectInfoDTO.getMemberProjectInfoDTOList()){

            MemberProjectInfo memberProjectInfo = new MemberProjectInfo();

            memberProjectInfo.setMemberProjectCategory(memberProjectDTO.getMemberProjectCategory());
            memberProjectInfo.setMemberProjectDetails(memberProjectDTO.getMemberProjectDetails());
            memberProjectInfo.setMemberProjectEndDate(memberProjectDTO.getIsCurrentlyWorking() ? null : LocalDate.parse(memberProjectDTO.getMemberProjectEndDate()));
            memberProjectInfo.setMemberProjectStartDate(LocalDate.parse(memberProjectDTO.getMemberProjectStartDate()));
            memberProjectInfo.setMemberProjectTitle(memberProjectDTO.getMemberProjectTitle());
            memberProjectInfo.setMemberProjectMemberRole(memberProjectDTO.getMemberProjectMemberRole());
            memberProjectInfo.setIsCurrentlyWorking(memberProjectDTO.getIsCurrentlyWorking());

            memberProjectInfo.setMember(member);

            memberProjectInfoRepository.save(memberProjectInfo);
        }

        return  ResponseEntity.ok("Member project info registered successfully!");

    }

    @Override
    public ResponseEntity<?> updateProject(MemberProjectInfoListDTO memberProjectInfoListDTO) {


        List<MemberProjectInfo> member = memberProjectInfoRepository.findByMemberId(memberProjectInfoListDTO.getMemberId());

        if(member.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Member not found with this id");
        }

        Member memberObj = memberRepository.findMemberById(memberProjectInfoListDTO.getMemberId());

        Set<Integer> memberProjectTypeList = memberProjectInfoListDTO.getMemberProjectInfoDTOList().stream()
                .map(MemberProjectInfoDTO::getId).collect(Collectors.toSet());

        Map<Integer, MemberProjectInfo> memberProjectInfoIdMap = memberProjectInfoRepository.findAllById(memberProjectTypeList).stream()
                .collect(Collectors.toMap(MemberProjectInfo::getId, memberProjectInfo -> memberProjectInfo, (pre, post) -> pre));

        for(MemberProjectInfoDTO memberProjectInfoDTO: memberProjectInfoListDTO.getMemberProjectInfoDTOList()){

            MemberProjectInfo existingMemberProjectInfo = memberProjectInfoIdMap.get(memberProjectInfoDTO.getId());

            if (existingMemberProjectInfo == null || !existingMemberProjectInfo.getMember().getId().equals(memberObj.getId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Member Project Info with this id not found for the specified member!");
            }


            existingMemberProjectInfo.setMemberProjectCategory(memberProjectInfoDTO.getMemberProjectCategory());
            existingMemberProjectInfo.setMemberProjectDetails(memberProjectInfoDTO.getMemberProjectDetails());
            existingMemberProjectInfo.setMemberProjectEndDate(memberProjectInfoDTO.getIsCurrentlyWorking() ? null : LocalDate.parse(memberProjectInfoDTO.getMemberProjectEndDate()));
            existingMemberProjectInfo.setMemberProjectStartDate(LocalDate.parse(memberProjectInfoDTO.getMemberProjectStartDate()));
            existingMemberProjectInfo.setMemberProjectTitle(memberProjectInfoDTO.getMemberProjectTitle());
            existingMemberProjectInfo.setMemberProjectMemberRole(memberProjectInfoDTO.getMemberProjectMemberRole());
            existingMemberProjectInfo.setIsCurrentlyWorking(memberProjectInfoDTO.getIsCurrentlyWorking());

            existingMemberProjectInfo.setMember(memberObj);

            memberProjectInfoRepository.save(existingMemberProjectInfo);

        }

        return ResponseEntity.ok("Member project info updated successfully!");
    }

}

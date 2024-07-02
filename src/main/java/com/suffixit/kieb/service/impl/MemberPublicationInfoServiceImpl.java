package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.MemberPublicationInfoProfileDTO;
import com.suffixit.kieb.dto.MemberPublicationListDTO;
import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberPublicationInfo;
import com.suffixit.kieb.repository.MemberPublicationInfoRepository;
import com.suffixit.kieb.repository.MemberRepository;
import com.suffixit.kieb.service.MemberPublicationInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class MemberPublicationInfoServiceImpl implements MemberPublicationInfoService {

    private final MemberPublicationInfoRepository memberPublicationInfoRepository;
    private final MemberRepository memberRepository;

    public MemberPublicationInfoServiceImpl(MemberPublicationInfoRepository memberPublicationInfoRepository, MemberRepository memberRepository) {
        this.memberPublicationInfoRepository = memberPublicationInfoRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public ResponseEntity<String> addMemberPublicationInfo(MemberPublicationListDTO memberPublicationListDTO) {

        Integer memberId = memberPublicationListDTO.getMemberId();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with id: " + memberId + " not found!");
        }

        for(MemberPublicationInfoProfileDTO memberPublicationInfoProfileDTO: memberPublicationListDTO.getMemberPublicationInfoProfileDTOList()){

            System.out.println(memberPublicationInfoProfileDTO.getPublicationUrl());
            System.out.println(memberPublicationInfoProfileDTO.getPublicationPublisher());
            MemberPublicationInfo memberPublicationInfo = new MemberPublicationInfo();

            memberPublicationInfo.setPublicationAuthor(memberPublicationInfoProfileDTO.getPublicationAuthor());
            memberPublicationInfo.setPublicationMonth(memberPublicationInfoProfileDTO.getPublicationMonth());
            memberPublicationInfo.setPublicationUrl(memberPublicationInfoProfileDTO.getPublicationUrl());
            memberPublicationInfo.setPublicationPage(memberPublicationInfoProfileDTO.getPublicationPage());
            memberPublicationInfo.setPublicationVolume(memberPublicationInfoProfileDTO.getPublicationVolume());
            memberPublicationInfo.setPublicationTitle(memberPublicationInfoProfileDTO.getPublicationTitle());
            memberPublicationInfo.setPublicationPublisher(memberPublicationInfoProfileDTO.getPublicationPublisher());
            memberPublicationInfo.setPublicationYear(memberPublicationInfoProfileDTO.getPublicationYear());
            memberPublicationInfo.setPublicationJournalConference(memberPublicationInfoProfileDTO.getPublicationJournalConference());
            memberPublicationInfo.setPublicationJournalConferenceName(memberPublicationInfoProfileDTO.getPublicationJournalConferenceName());
            memberPublicationInfo.setMember(member);


            memberPublicationInfoRepository.save(memberPublicationInfo);

        }

        return ResponseEntity.ok("Member publication Info added successfully!");
    }

    @Override
    public ResponseEntity<String> updatePublication(MemberPublicationListDTO memberPublicationListDTO) {

        Optional<Member> existingMember = memberRepository.findById(memberPublicationListDTO.getMemberId());

        if(existingMember.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found with this id");
        }

        Member member = existingMember.get();

        Set<Integer> memberPublicationTypeList = memberPublicationListDTO.getMemberPublicationInfoProfileDTOList().stream()
                .map(MemberPublicationInfoProfileDTO::getId).collect(Collectors.toSet());

        Map<Integer, MemberPublicationInfo> memberPublicationInfoIdMap = memberPublicationInfoRepository.findAllById(memberPublicationTypeList).stream()
                .collect(Collectors.toMap(MemberPublicationInfo::getId, memberPublicationInfo -> memberPublicationInfo, (pre, post) -> pre));

        for(MemberPublicationInfoProfileDTO memberPublicationInfoProfileDTO: memberPublicationListDTO.getMemberPublicationInfoProfileDTOList()){

            Optional<MemberPublicationInfo> existingMemberPublicationInfo= Optional.ofNullable(memberPublicationInfoIdMap.get(memberPublicationInfoProfileDTO.getId()));


            if(existingMemberPublicationInfo.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member Publication Info with this id not found!");
            }

            MemberPublicationInfo memberPublicationInfo = existingMemberPublicationInfo.get();

            memberPublicationInfo.setPublicationAuthor(memberPublicationInfoProfileDTO.getPublicationAuthor());
            memberPublicationInfo.setPublicationMonth(memberPublicationInfoProfileDTO.getPublicationMonth());
            memberPublicationInfo.setPublicationUrl(memberPublicationInfoProfileDTO.getPublicationUrl());
            memberPublicationInfo.setPublicationPage(memberPublicationInfoProfileDTO.getPublicationPage());
            memberPublicationInfo.setPublicationVolume(memberPublicationInfoProfileDTO.getPublicationVolume());
            memberPublicationInfo.setPublicationTitle(memberPublicationInfoProfileDTO.getPublicationTitle());
            memberPublicationInfo.setPublicationPublisher(memberPublicationInfoProfileDTO.getPublicationPublisher());
            memberPublicationInfo.setPublicationYear(memberPublicationInfoProfileDTO.getPublicationYear());
            memberPublicationInfo.setPublicationJournalConference(memberPublicationInfoProfileDTO.getPublicationJournalConference());
            memberPublicationInfo.setPublicationJournalConferenceName(memberPublicationInfoProfileDTO.getPublicationJournalConferenceName());
            memberPublicationInfo.setMember(member);

            memberPublicationInfoRepository.save(memberPublicationInfo);

        }

        return ResponseEntity.ok("Member project info updated successfully!");
    }

    @Override
    public ResponseEntity<String> deletePublicationsByMemberId(Integer memberId) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if(optionalMember.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found with this id!");
        }

        Member member = optionalMember.get();

        List<MemberPublicationInfo> memberPublicationInfoList = memberPublicationInfoRepository.findByMember(member);

        memberPublicationInfoRepository.deleteAll(memberPublicationInfoList);

        return ResponseEntity.ok("Members publication info deleted successfully!");
    }

}


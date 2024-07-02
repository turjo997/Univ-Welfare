package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.MemberEducationInfoDTO;
import com.suffixit.kieb.dto.MemberSocialLinkDTO;
import com.suffixit.kieb.dto.MemberSocialLinkListDTO;
import com.suffixit.kieb.dto.SocialLinkDTO;
import com.suffixit.kieb.entities.*;
import com.suffixit.kieb.repository.MemberRepository;
import com.suffixit.kieb.repository.MemberSocialLinkRepository;
import com.suffixit.kieb.repository.SocialLinkRepository;
import com.suffixit.kieb.service.MemberSocialLinkService;
import jakarta.transaction.Transactional;
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
public class MemberSocialLinkServiceImpl implements MemberSocialLinkService {

    private final MemberSocialLinkRepository memberSocialLinkRepository;
    private final MemberRepository memberRepository;
    private final SocialLinkRepository socialLinkRepository;


    MemberSocialLinkServiceImpl(MemberSocialLinkRepository memberSocialLinkRepository, MemberRepository memberRepository, SocialLinkRepository socialLinkRepository){
        this.memberSocialLinkRepository = memberSocialLinkRepository;
        this.memberRepository = memberRepository;
        this.socialLinkRepository = socialLinkRepository;
    }
    @Override
    public ResponseEntity<String> addMemberSocialLink(MemberSocialLinkListDTO memberSocialLinkListDTO){

        Integer memberId = memberSocialLinkListDTO.getMemberId();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with id: " + memberId + " not found!");
        }

        Set<Integer> memberSocialLinkList = memberSocialLinkListDTO.getMemberSocialLinkDTOs().stream().map(MemberSocialLinkDTO::getLinkTypeId).collect(Collectors.toSet());

        Map<Integer, SocialLink> socialLinkIdMap = socialLinkRepository.findAllById(memberSocialLinkList).stream()
                .collect(Collectors.toMap(SocialLink::getId, socialLink -> socialLink, (pre, post) -> pre));

        for (MemberSocialLinkDTO memberSocialLinkDTO : memberSocialLinkListDTO.getMemberSocialLinkDTOs()) {

            SocialLink socialLink = socialLinkIdMap.get(memberSocialLinkDTO.getLinkTypeId());

            if (socialLink== null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Social Link not found with id:"+ memberSocialLinkDTO.getLinkTypeId());
            }

            MemberSocialLink memberSocialLink = new MemberSocialLink();

            memberSocialLink.setLink(memberSocialLinkDTO.getLink());
            memberSocialLink.setLinkType(socialLink);


            memberSocialLink.setMember(member);

            memberSocialLinkRepository.save(memberSocialLink);
        }

        return ResponseEntity.ok("Member Social Link created successfully!");
    }

    @Override
    public ResponseEntity<String> modifyMemberSocialLink(MemberSocialLinkListDTO memberSocialLinkListDTO){
//
//        Integer memberId = memberSocialLinkListDTO.getMemberId();
//
//        if(memberId == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with this id is not found!");
//        }

        Integer memberId = memberSocialLinkListDTO.getMemberId();

        List<MemberSocialLink> member = memberSocialLinkRepository.findByMemberId(memberId);

        if(member.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Member not found with this id");
        }

        Member memberObj = memberRepository.findMemberById(memberSocialLinkListDTO.getMemberId());




        Set<Integer> memberSocialLinkList = memberSocialLinkListDTO.getMemberSocialLinkDTOs().stream().map(MemberSocialLinkDTO::getId).collect(Collectors.toSet());

        Map<Integer, MemberSocialLink> memberSocialLinkIdMap = memberSocialLinkRepository.findAllById(memberSocialLinkList).stream()
                .collect(Collectors.toMap(MemberSocialLink::getId, memberSocialLink -> memberSocialLink, (pre, post) -> pre));

        Set<Integer> socialLinkTypeList = memberSocialLinkListDTO.getMemberSocialLinkDTOs().stream().map(MemberSocialLinkDTO::getLinkTypeId).collect(Collectors.toSet());

        Map<Integer, SocialLink> socialLinkIdMap = socialLinkRepository.findAllById(socialLinkTypeList).stream()
                .collect(Collectors.toMap(SocialLink::getId, socialLink -> socialLink, (pre, post) -> pre));

        for(MemberSocialLinkDTO memberSocialLinkDTO : memberSocialLinkListDTO.getMemberSocialLinkDTOs()){

            MemberSocialLink existingMemberSocialLink = memberSocialLinkIdMap.get(memberSocialLinkDTO.getId());


            if (existingMemberSocialLink == null || !existingMemberSocialLink.getMember().getId().equals(memberObj.getId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Member Social Link with this id not found!");
            }


            SocialLink socialLink = socialLinkIdMap.get(memberSocialLinkDTO.getLinkTypeId());

            existingMemberSocialLink.setMember(memberObj);
            existingMemberSocialLink.setLinkType(socialLink);
            existingMemberSocialLink.setLink(memberSocialLinkDTO.getLink());

            memberSocialLinkRepository.save(existingMemberSocialLink);

        }

        return ResponseEntity.ok("Member Social Link modified successfully!");
    }



}

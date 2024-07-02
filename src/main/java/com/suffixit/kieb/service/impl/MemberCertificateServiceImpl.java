package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.MemberCertificateDTO;
import com.suffixit.kieb.dto.MemberCertificateInfoListDTO;
import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.MemberCertificate;
import com.suffixit.kieb.repository.MemberCertificateRepository;
import com.suffixit.kieb.repository.MemberRepository;
import com.suffixit.kieb.service.MemberCertificateService;
import com.suffixit.kieb.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class MemberCertificateServiceImpl implements MemberCertificateService {

    private final MemberCertificateRepository memberCertificateRepository;

    private final MemberRepository memberRepository;

    private final String uploadDir;

    public MemberCertificateServiceImpl(MemberCertificateRepository memberCertificateRepository, MemberRepository memberRepository, @Value("${fileStore.directory}") String uploadDir){
        this.memberCertificateRepository = memberCertificateRepository;
        this.memberRepository = memberRepository;
        this.uploadDir  = uploadDir;
    }

    @Override
    public ResponseEntity<String> addMemberCertificate(MemberCertificateInfoListDTO memberCertificateInfoListDTO){

        Integer memberId = memberCertificateInfoListDTO.getMemberId();

        Member member = memberRepository.findMemberById(memberId);

        if(member == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with this id is not found!");
        }

        long totalCount = memberCertificateRepository.countByMemberId(memberId);

        if(totalCount >= 3){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "Already exceed ! You can't add more");
        }

        for(MemberCertificateDTO memberCertificateDTO: memberCertificateInfoListDTO.getMemberCertificateList()){


            MemberCertificate memberCertificateInfo = new MemberCertificate();

            memberCertificateInfo.setCertificateLongName(memberCertificateDTO.getCertificateLongName());
            memberCertificateInfo.setCertificateShortName(memberCertificateDTO.getCertificateShortName());

            String memberCertificateDir = uploadDir + "/member-doc/certificates";

            try{
                String fileName = member.getId() + "_" + memberCertificateDTO.getCertificateFileName().getOriginalFilename();
                FileUploadUtil.saveFile(memberCertificateDir, fileName, memberCertificateDTO.getCertificateFileName());

                memberCertificateInfo.setCertificateFileName(fileName);
            }
            catch(IOException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File uploaded failed!");
            }

            memberCertificateInfo.setMember(member);

            memberCertificateRepository.save(memberCertificateInfo);


        }

        return ResponseEntity.ok("Member certificate created  successfully!");
    }

    @Override
    public ResponseEntity<String> modifyMemberCertificate(MemberCertificateInfoListDTO memberCertificateInfoListDTO){

        Integer memberId = memberCertificateInfoListDTO.getMemberId();

        Member member = memberRepository.findMemberById(memberId);

        if(member == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this id not found!");
        }

        Set<Integer> memberCertificateList = memberCertificateInfoListDTO.getMemberCertificateList().stream().map(MemberCertificateDTO::getId).collect(Collectors.toSet());

        Map<Integer, MemberCertificate> certificateIdMap = memberCertificateRepository.findAllById(memberCertificateList).stream()
                .collect(Collectors.toMap(MemberCertificate::getId, memberCertificate -> memberCertificate, (pre, post) -> pre));

        int iterationCount = 0;

        for(MemberCertificateDTO memberCertificateDTO : memberCertificateInfoListDTO.getMemberCertificateList()){

            if(iterationCount >=3){
                break;
            }

            Optional<MemberCertificate> existingMemberCertificate = Optional.ofNullable(certificateIdMap.get(memberCertificateDTO.getId()));

            if(existingMemberCertificate.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member Certificate with this id not found!");
            }

            MemberCertificate memberCertificate = existingMemberCertificate.get();

            memberCertificate.setCertificateShortName(memberCertificateDTO.getCertificateShortName());
            memberCertificate.setCertificateLongName(memberCertificateDTO.getCertificateLongName());

            String memberCertificateDir = uploadDir + "/member-doc/certificates";

            try{
                String fileName = member.getId() + "_" + memberCertificateDTO.getCertificateFileName().getOriginalFilename();
                FileUploadUtil.saveFile(memberCertificateDir, fileName, memberCertificateDTO.getCertificateFileName());

                memberCertificate.setCertificateFileName(fileName);
            }
            catch(IOException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File uploaded failed!");
            }

            memberCertificate.setMember(member);

            memberCertificateRepository.save(memberCertificate);

            iterationCount++;
        }

        return ResponseEntity.ok("Member Certificate modified successfully");
    }

    @Override
    public ResponseEntity<?> getAMemberCertificateForAMember(Integer id) {

//        Optional<MemberCertificate>  existingMemberCertificate = memberCertificateRepository.findById(id);
//
//        if(!existingMemberCertificate.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Certificate with this id is not found!");
//        }
//        MemberCertificate memberCertificate = existingMemberCertificate.get();
//
//        MemberCertificateProfileDTO memberCertificateProfileDTO = MemberCertificateProfileDTO.builder()
//                .id(memberCertificate.getId())
//                .certificateFileName(memberCertificate.getCertificateFileName())
//                .certificateLongName(memberCertificate.getCertificateLongName())
//                .certificateShortName(memberCertificate.getCertificateShortName())
//                .build();
//
//        return ResponseEntity.ok(memberCertificateProfileDTO);
//    }
        return ResponseEntity.ok("memberCertificateProfileDTO");
    }


}

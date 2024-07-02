package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.CommitteeDTO;
import com.suffixit.kieb.dto.CommitteeMemberDTO;
import com.suffixit.kieb.dto.MemberDesignationDTO;
import com.suffixit.kieb.entities.*;
import com.suffixit.kieb.repository.CommitteeDesignationRepository;
import com.suffixit.kieb.repository.CommitteeRepository;
import com.suffixit.kieb.repository.MemberCommitteeRepository;
import com.suffixit.kieb.repository.MemberRepository;
import com.suffixit.kieb.service.CommitteeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommitteeServiceImpl implements CommitteeService {

    private final CommitteeRepository committeeRepository;

    private final MemberRepository memberRepository;

    private final MemberCommitteeRepository memberCommitteeRepository;

    private final CommitteeDesignationRepository committeeDesignationRepository;

    public CommitteeServiceImpl(CommitteeRepository committeeRepository, MemberRepository memberRepository, MemberCommitteeRepository memberCommitteeRepository, CommitteeDesignationRepository committeeDesignationRepository) {
        this.committeeRepository = committeeRepository;
        this.memberRepository = memberRepository;
        this.memberCommitteeRepository = memberCommitteeRepository;
        this.committeeDesignationRepository = committeeDesignationRepository;
    }
    @Override
    public ResponseEntity<String> addCommittee(CommitteeDTO committeeDTO) {

        byte defaultStatus = 1;

        byte status = (committeeDTO.getStatus() != null) ? committeeDTO.getStatus() : defaultStatus;

        Committee committee = Committee.builder()
                .committeeType("Centre Committee")
                .committeeName(committeeDTO.getCommitteeName())
                .committeeShortName(committeeDTO.getCommitteeShortName())
                .committeeDuration(committeeDTO.getCommitteeDuration())
                .status(status)
                .build();

        committeeRepository.save(committee);

        return ResponseEntity.ok("Committee added successfully!");
    }

    @Override
    public ResponseEntity<String> updateCommitteeWithStatus(CommitteeMemberDTO committeeMemberDTO){
        Integer committeeId = committeeMemberDTO.getCommitteeId();

        Optional<Committee> optionalCommittee = committeeRepository.findCommitteeById(committeeId);

        if(optionalCommittee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Committee not found with this id");
        }

        Committee committee = optionalCommittee.get();

        committee.setStatus(committeeMemberDTO.getStatus());

        committeeRepository.save(committee);

        return ResponseEntity.ok("Committee status has been changed!");
    }

    @Override
    public ResponseEntity<String> addMemberAndDesignationInCommittee(CommitteeMemberDTO committeeMemberDTO){
        Integer committeeId = committeeMemberDTO.getCommitteeId();
        Optional<Committee> optionalCommittee = committeeRepository.findCommitteeById(committeeId);

        if(optionalCommittee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Committee not found wih this id");
        }

        Committee committee = optionalCommittee.get();

        Set<String> memberList = committeeMemberDTO.getMemberDesignationDTOList()
                .stream()
                .map(MemberDesignationDTO::getRoll)
                .collect(Collectors.toSet());

        Map<String, Member> memberIdMap = memberRepository.findAllByRollIn(memberList)
                .stream()
                .collect(Collectors.toMap(Member::getRoll, member -> member, (pre, post) -> pre));

        Set<Integer> designationList = committeeMemberDTO.getMemberDesignationDTOList().stream().map(MemberDesignationDTO::getDesignationId).collect(Collectors.toSet());

        Map<Integer, CommitteeDesignation> designationIdMap = committeeDesignationRepository.findAllById(designationList).stream()
                .collect(Collectors.toMap(CommitteeDesignation::getId, committeeDesignation -> committeeDesignation, (pre, post) -> pre));

        for (MemberDesignationDTO memberDesignationDTO : committeeMemberDTO.getMemberDesignationDTOList()) {

            Member member = memberIdMap.get(memberDesignationDTO.getRoll());

            if (member == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id!");
            }

            CommitteeDesignation committeeDesignation = designationIdMap.get(memberDesignationDTO.getDesignationId());

            if (committeeDesignation== null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Committee Designation not found with id!");
            }

            CommitteeMember committeeMember = new CommitteeMember();

            committeeMember.setMember(member);
            committeeMember.setCommitteeDesignation(committeeDesignation);


            committeeMember.setCommittee(committee);

            memberCommitteeRepository.save(committeeMember);
        }

        return ResponseEntity.ok("Committee Member added successfully!");

    }

    @Override
    public ResponseEntity<?> getAllCommittee(){

        List<Committee> committees = committeeRepository.findAll();

        if(committees.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No committees found");
        }

        else{
            return ResponseEntity.ok(committees);
        }

    }
    @Override
    public ResponseEntity<?> getAllMemberCommittee(){
        List<CommitteeMember>  committeeMembers = memberCommitteeRepository.findAll();

        //List<Committee> committee = committeeRepository.findAll();

        for(CommitteeMember  comMember: committeeMembers){
            Committee commit = comMember.getCommittee();

            System.out.println("committee name: " + commit.getCommitteeName());
        }

        if(committeeMembers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No committee member found!");
        }
        else{
            return ResponseEntity.ok(committeeMembers);
        }
    }
//    @Override
//    public ResponseEntity<?> deleteCommittee(Integer id){
//
//        Byte inActiveStatus = 0;
//
//        List<Committee> committeeWithInactiveStatus = committeeRepository.findByStatus(inActiveStatus);
//
//        if(committeeWithInactiveStatus.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Committee with inactive status not found!");
//        }
//
//        List<CommitteeMember> committeeMembersToDelete = committeeWithInactiveStatus.stream()
//                .flatMap(committee -> memberCommitteeRepository.findByCommitteeIdAndStatus(committee.getId(), inActiveStatus).stream())
//                .collect(Collectors.toList());
//
//        memberCommitteeRepository.deleteAll(committeeMembersToDelete);
//
//        return ResponseEntity.ok("Member committee deleted successfully!");
//
//    }
    @Override
    public ResponseEntity<String> deleteMembersByCommitteeId(Integer committeeId) {

        Optional<Committee> optionalCommittee = committeeRepository.findCommitteeById(committeeId);

        if (optionalCommittee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Committee not found with this id");
        }

        Committee committee = optionalCommittee.get();

        List<CommitteeMember> committeeMembers = memberCommitteeRepository.findByCommittee(committee);

        memberCommitteeRepository.deleteAll(committeeMembers);

        return ResponseEntity.ok("Committee Members deleted successfully!");
    }



}

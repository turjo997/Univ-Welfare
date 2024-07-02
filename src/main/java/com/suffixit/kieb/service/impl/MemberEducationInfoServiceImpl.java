package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.MemberEducationInfoDTO;
import com.suffixit.kieb.dto.MemberEducationInfoListDTO;
import com.suffixit.kieb.entities.*;
import com.suffixit.kieb.repository.*;
import com.suffixit.kieb.service.MemberEductionInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class MemberEducationInfoServiceImpl implements MemberEductionInfoService {

    private final MemberEducationInfoRepository memberEducationInfoRepository;

    private final DegreeRepository degreeRepository;

    private final MemberRepository memberRepository;

    private final ResultTypeRepository resultTypeRepository;

    private final UniversityRepository universityRepository;

    private final MemberDivisionRepository memberDivisionRepository;

    public MemberEducationInfoServiceImpl(MemberEducationInfoRepository memberEducationInfoRepository, DegreeRepository degreeRepository, MemberRepository memberRepository, ResultTypeRepository resultTypeRepository, UniversityRepository universityRepository, MemberDivisionRepository memberDivisionRepository) {

        this.memberEducationInfoRepository = memberEducationInfoRepository;
        this.degreeRepository = degreeRepository;
        this.resultTypeRepository = resultTypeRepository;
        this.universityRepository = universityRepository;
        this.memberRepository = memberRepository;
        this.memberDivisionRepository = memberDivisionRepository;
    }

    @Override
    public ResponseEntity<String> addMemberEducationInfo(MemberEducationInfoListDTO memberEducationInfoListDTO) {

        Integer memberId = memberEducationInfoListDTO.getMemberId();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with id: " + memberId + " not found!");
        }

        long totalCount = memberEducationInfoRepository.countByMemberId(memberId);

        if(totalCount >= 3){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "Already exceed ! You can't add more");
        }

        Set<Integer> universityList = memberEducationInfoListDTO.getEducationInfoList()
                .stream().map(MemberEducationInfoDTO::getBoardUniversityId).collect(Collectors.toSet());

        Map<Integer, University> universityIdMap = universityRepository.findAllById(universityList).stream()
                .collect(Collectors.toMap(University::getId, university -> university, (pre, post) -> pre));

        Set<Integer> degreeTypeList = memberEducationInfoListDTO.getEducationInfoList()
                .stream().map(MemberEducationInfoDTO::getDegreeTypeId).collect(Collectors.toSet());

        Map<Integer, Degree> degreeIdMap = degreeRepository.findAllById(degreeTypeList).stream()
                .collect(Collectors.toMap(Degree::getId, degree -> degree, (pre, post) -> pre));

        Set<Integer> resultTypeList = memberEducationInfoListDTO.getEducationInfoList()
                .stream().map(MemberEducationInfoDTO::getResultTypeId).collect(Collectors.toSet());

        Map<Integer, ResultType> resultTypeIdMap = resultTypeRepository.findAllById(resultTypeList).stream()
                .collect(Collectors.toMap(ResultType::getId, resultType -> resultType, (pre, post) -> pre));


        for (MemberEducationInfoDTO memberEducationInfoDTO : memberEducationInfoListDTO.getEducationInfoList()) {


            Degree degree = degreeIdMap.get(memberEducationInfoDTO.getDegreeTypeId());

            if (degree == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Degree not found with id:" + memberEducationInfoDTO.getDegreeTypeId());
            }

            MemberEducationInfo memberEducationInfo = new MemberEducationInfo();


            if (Objects.equals(degree.getDegreeName(), "BSC")) {

                ResultType resultType = resultTypeIdMap.get(memberEducationInfoDTO.getResultTypeId());

                if (resultType == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Result Type  not found with id: " + memberEducationInfoDTO.getResultTypeId());
                }

                if(!Objects.equals(resultType.getResultTypeName() , "CGPA")){
                    throw new ResponseStatusException(HttpStatus.CONFLICT , "ResultType should be CGPA");
                }

                String result = memberEducationInfoDTO.getResult();
                String decimalPattern = "^(?:[0-4](?:\\.[0-9]{1,3})?|4(?:\\.000?)?)$";


                if (!result.matches(decimalPattern)) {
                    throw new BadCredentialsException("Result is invalid");
                }

                MemberDivision memberDivision = null;

                if (memberEducationInfoDTO.getMemberDivisionId() !=null){
                     memberDivision = memberDivisionRepository.findById(memberEducationInfoDTO.getMemberDivisionId())
                            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "Member division not found"));

                }

                memberEducationInfo.setSubject(memberDivision  != null ? memberDivision.getFullName() : null);
                memberEducationInfo.setDegreeType(degree);
                memberEducationInfo.setInstituteName("Khulna University Of Engineering And Technology");
                memberEducationInfo.setYearOfPassing(memberEducationInfoDTO.getYearOfPassing());
                memberEducationInfo.setResultType(resultType);
                memberEducationInfo.setResult(result);
                memberEducationInfo.setMember(member);
                memberEducationInfoRepository.save(memberEducationInfo);
            } else {

                University university = universityIdMap.get(memberEducationInfoDTO.getBoardUniversityId());

                if (university == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Board not found with id:" + memberEducationInfoDTO.getBoardUniversityId());
                }

                ResultType resultType = resultTypeIdMap.get(memberEducationInfoDTO.getResultTypeId());

                if (resultType == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result Type  not found with id: " + memberEducationInfoDTO.getResultTypeId());
                }


                if(!Objects.equals(resultType.getResultTypeName() , "GPA")){
                    throw new ResponseStatusException(HttpStatus.CONFLICT , "ResultType should be GPA");
                }

                String result = memberEducationInfoDTO.getResult();
                String decimalPattern = "^(?:[0-4](?:\\.[0-9]{1,3})?|5(?:\\.000?)?)$";


                if (!result.matches(decimalPattern)) {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "Result is invalid");
                }

                memberEducationInfo.setDegreeType(degree);
                memberEducationInfo.setInstituteName(memberEducationInfoDTO.getInstituteName());
                memberEducationInfo.setBoardUniversity(university);
                memberEducationInfo.setYearOfPassing(memberEducationInfoDTO.getYearOfPassing());
                memberEducationInfo.setResultType(resultType);
                memberEducationInfo.setResult(result);
                memberEducationInfo.setMember(member);

                memberEducationInfoRepository.save(memberEducationInfo);

            }

        }

        return ResponseEntity.ok("Member education info created successfully!");
    }

    @Override
    public ResponseEntity<String> modifyMemberEducationInfo(MemberEducationInfoListDTO memberEducationInfoListDTO) {

        Integer memberId = memberEducationInfoListDTO.getMemberId();

        List<MemberEducationInfo> memberEducationInfoObj = memberEducationInfoRepository.findByMemberId(memberId);

        if(memberEducationInfoObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , " Member with this id not found!");
        }

        Member member = memberRepository.findMemberById(memberId);


        Set<Integer> universityList = memberEducationInfoListDTO.getEducationInfoList().stream().map(MemberEducationInfoDTO::getBoardUniversityId).collect(Collectors.toSet());

        Map<Integer, University> universityIdMap = universityRepository.findAllById(universityList).stream()
                .collect(Collectors.toMap(University::getId, university -> university, (pre, post) -> pre));

        Set<Integer> degreeTypeList = memberEducationInfoListDTO.getEducationInfoList().stream().map(MemberEducationInfoDTO::getDegreeTypeId).collect(Collectors.toSet());

        Map<Integer, Degree> degreeIdMap = degreeRepository.findAllById(degreeTypeList).stream()
                .collect(Collectors.toMap(Degree::getId, degree -> degree, (pre, post) -> pre));

        Set<Integer> resultTypeList = memberEducationInfoListDTO.getEducationInfoList().stream().map(MemberEducationInfoDTO::getResultTypeId).collect(Collectors.toSet());

        Map<Integer, ResultType> resultTypeIdMap = resultTypeRepository.findAllById(resultTypeList).stream()
                .collect(Collectors.toMap(ResultType::getId, resultType -> resultType, (pre, post) -> pre));

        Set<Integer> educationInfoList = memberEducationInfoListDTO.getEducationInfoList().stream().map(MemberEducationInfoDTO::getId).collect(Collectors.toSet());

        Map<Integer, MemberEducationInfo> educationIdMap = memberEducationInfoRepository.findAllById(educationInfoList).stream()
                .collect(Collectors.toMap(MemberEducationInfo::getId, educationInfo -> educationInfo, (pre, post) -> pre));


        for(MemberEducationInfoDTO memberEducationInfoDTO: memberEducationInfoListDTO.getEducationInfoList()){

            Optional<MemberEducationInfo> existingMemberEducationInfo = Optional.ofNullable(educationIdMap.get(memberEducationInfoDTO.getId()));


            if(existingMemberEducationInfo.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Member Education info with this id not found!");
            }

            MemberEducationInfo memberEducationInfo = existingMemberEducationInfo.get();

            Degree degree = degreeIdMap.get(memberEducationInfoDTO.getDegreeTypeId());



            if(Objects.equals(degree.getDegreeName(), "BSC")){

                ResultType resultType = resultTypeIdMap.get(memberEducationInfoDTO.getResultTypeId());

                if (resultType == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Result Type  not found with id: "+ memberEducationInfoDTO.getResultTypeId());
                }

                if(!Objects.equals(resultType.getResultTypeName() , "CGPA")){
                    throw new ResponseStatusException(HttpStatus.CONFLICT , "ResultType should be CGPA");
                }

                String result = memberEducationInfoDTO.getResult();
                String decimalPattern = "^(?:[0-4](?:\\.[0-9]{1,3})?|4(?:\\.000?)?)$";


                if (!result.matches(decimalPattern)) {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "Result is invalid");
                }

                memberEducationInfo.setDegreeType(degree);
                memberEducationInfo.setResultType(resultType);
                memberEducationInfo.setInstituteName(memberEducationInfoDTO.getInstituteName());
                memberEducationInfo.setYearOfPassing(memberEducationInfoDTO.getYearOfPassing());
                //memberEducationInfo.setSubject(member.getMemberDivision().getFullName()
                //!= null ? member.getMemberDivision().getFullName() : null);
                memberEducationInfo.setResult(memberEducationInfoDTO.getResult());

                memberEducationInfo.setMember(member);

                memberEducationInfoRepository.save(memberEducationInfo);

            }

            else{

                University university = universityIdMap.get(memberEducationInfoDTO.getBoardUniversityId());

                if (university == null) {
                    throw new BadCredentialsException("University not found with id:"+ memberEducationInfoDTO.getBoardUniversityId());
                }

                ResultType resultType = resultTypeIdMap.get(memberEducationInfoDTO.getResultTypeId());

                if (resultType == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Result Type  not found with id: "+memberEducationInfoDTO.getResultTypeId());
                }

                if(!Objects.equals(resultType.getResultTypeName() , "GPA")){
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "ResultType should be GPA");
                }

                String result = memberEducationInfoDTO.getResult();
                String decimalPattern = "^(?:[0-4](?:\\.[0-9]{1,3})?|5(?:\\.000?)?)$";


                if (!result.matches(decimalPattern)) {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE , "Result is invalid");
                }

                memberEducationInfo.setDegreeType(degree);
                memberEducationInfo.setResultType(resultType);
                memberEducationInfo.setBoardUniversity(university);
                memberEducationInfo.setInstituteName(memberEducationInfoDTO.getInstituteName());
                memberEducationInfo.setYearOfPassing(memberEducationInfoDTO.getYearOfPassing());
                memberEducationInfo.setResult(memberEducationInfoDTO.getResult());

                memberEducationInfo.setMember(member);

                memberEducationInfoRepository.save(memberEducationInfo);

            }

        }

        return ResponseEntity.ok("Member Education info modified successfully!");
    }

    @Override
    public List<MemberEducationInfo> getAllEducationInfoForMember(Integer memberId) {
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with id: " + memberId + " not found!");
        }

        return memberEducationInfoRepository.findByMember(member);
    }


}

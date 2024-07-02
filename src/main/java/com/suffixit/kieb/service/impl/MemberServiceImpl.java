package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.*;
import com.suffixit.kieb.entities.*;
import com.suffixit.kieb.enumerations.ApproveStatus;
import com.suffixit.kieb.repository.*;
import com.suffixit.kieb.service.MemberService;
import com.suffixit.kieb.utils.FileUploadUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.suffixit.kieb.utils.GenderUtil.mapGender;

@Service
@Validated
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final MemberEducationInfoRepository memberEducationInfoRepository;
    private final MemberProfessionalInfoRepository memberProfessionalInfoRepository;
    private final YearRepository yearRepository;
    private final UniversitySubjectRepository universitySubjectRepository;
    private final MemberProjectInfoRepository memberProjectInfoRepository;
    private final MemberAddressRepository memberAddressRepository;
    private final MemberCertificateRepository memberCertificateRepository;

    private final MemberSocialLinkRepository memberSocialLinkRepository;

    private final MemberPublicationInfoRepository memberPublicationInfoRepository;

    private final String uploadDir;

    public MemberServiceImpl(MemberRepository memberRepository, UserRepository userRepository, MemberEducationInfoRepository memberEducationInfoRepository, MemberProfessionalInfoRepository memberProfessionalInfoRepository, YearRepository yearRepository, UniversitySubjectRepository universitySubjectRepository, MemberProjectInfoRepository memberProjectInfoRepository, MemberAddressRepository memberAddressRepository, MemberCertificateRepository memberCertificateRepository, MemberSocialLinkRepository memberSocialLinkRepository, MemberPublicationInfoRepository memberPublicationInfoRepository, @Value("${fileStore.directory}") String uploadDir) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.memberEducationInfoRepository = memberEducationInfoRepository;
        this.memberProfessionalInfoRepository = memberProfessionalInfoRepository;
        this.yearRepository = yearRepository;
        this.universitySubjectRepository = universitySubjectRepository;
        this.memberProjectInfoRepository = memberProjectInfoRepository;
        this.memberAddressRepository = memberAddressRepository;
        this.memberCertificateRepository = memberCertificateRepository;
        this.memberSocialLinkRepository = memberSocialLinkRepository;
        this.memberPublicationInfoRepository = memberPublicationInfoRepository;
        this.uploadDir = uploadDir;

    }

    private static String convertToFullYear(String twoDigitYear) {

        int currentYear = LocalDate.now().getYear();
        int currentCentury = currentYear / 100;

        int twoDigitYearValue = Integer.parseInt(twoDigitYear);
        int century;

        if (twoDigitYearValue >= 0 && twoDigitYearValue <= 20) {
            century = currentCentury;
        } else {
            century = currentCentury - 1;
        }
        int fullYear = century * 100 + twoDigitYearValue;

        return String.valueOf(fullYear);
    }

    @Override
    public ResponseEntity<String> registerMember(MemberRegisteredDTO memberRegisteredDTO) {


        if (memberRepository.existsByEmailId(memberRegisteredDTO.getEmailId())) {

            String errorMessage = "Member with the same email already exists";
            return ResponseEntity.badRequest().body(errorMessage);

        }

        if (memberRepository.existsByRollAndApproveStatus(memberRegisteredDTO.getRollNo(), ApproveStatus.PENDING)) {
            String errorMessage = "Member with the same roll and pending status already exists";

            return ResponseEntity.badRequest().body(errorMessage);
        }

        if (memberRepository.existsByRollAndApproveStatus(memberRegisteredDTO.getRollNo(), ApproveStatus.APPROVED)) {
            String errorMessage = "Member with the same roll and approved status already exists";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        String batchCode = memberRegisteredDTO.getRollNo().substring(2, 4);
        Optional<UniversitySubject> existingUniversitySubject = Optional.ofNullable(universitySubjectRepository.findBySubjectCode(batchCode));

        if (existingUniversitySubject.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Subject Name with this code not found!");

        }
        UniversitySubject universitySubject = existingUniversitySubject.get();
        Member member = Member.builder().memberName(memberRegisteredDTO.getMemberName()).phone1(memberRegisteredDTO.getPhoneOne()).phone2(memberRegisteredDTO.getPhoneTwo()).emailId(memberRegisteredDTO.getEmailId()).roll(String.valueOf(memberRegisteredDTO.getRollNo())).bloodGroup(memberRegisteredDTO.getBloodGroup()).gender(memberRegisteredDTO.getGender()).universitySubject(universitySubject).build();
        member.setApproveStatus(ApproveStatus.PENDING);
        memberRepository.save(member);
        return ResponseEntity.ok("Member registered successfully!");

    }

    @Override
    @Transactional
    public ResponseEntity<String> modifyMember(Integer id, MemberDTO memberDTO) {
        /// text size should be fixed
        Optional<Member> existingMember = memberRepository.findById(id);
        if (existingMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with this id not found!");
        }
        Optional<Users> existingUser = userRepository.findByMemberId(existingMember.get().getId());
        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        Users user = existingUser.get();
        user.setEmail(memberDTO.getEmailId());
        Member member = existingMember.get();
        member.setEmailId(memberDTO.getEmailId() != null ? memberDTO.getEmailId() : member.getEmailId());
        member.setMemberName(memberDTO.getMemberName() != null ? memberDTO.getMemberName() : member.getMemberName());
        member.setBloodGroup(memberDTO.getBloodGroup() != null ? memberDTO.getBloodGroup() : member.getBloodGroup());
        member.setDob(memberDTO.getDob() != null ? memberDTO.getDob() : member.getDob());
        member.setPlaceOfBirth(memberDTO.getPlaceOfBirth() != null ? memberDTO.getPlaceOfBirth() : member.getPlaceOfBirth());
        member.setFatherName(memberDTO.getFatherName() != null ? memberDTO.getFatherName() : member.getFatherName());
        member.setMotherName(memberDTO.getMotherName() != null ? memberDTO.getMotherName() : member.getMotherName());
        member.setMobile(memberDTO.getMobile() != null ? memberDTO.getMobile() : member.getMobile());
        member.setPhone1(memberDTO.getPhone1() != null ? memberDTO.getPhone1() : member.getPhone1());
        member.setPhone2(memberDTO.getPhone2() != null ? memberDTO.getPhone2() : member.getPhone2());
        memberRepository.save(member);
        userRepository.save(user);

        return ResponseEntity.ok("Member modifying successfully!");
    }

    @Override
    public ResponseEntity<?> memberProfile(Integer id) {

        Optional<Member> singleMember = memberRepository.findById(id);

        if (singleMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member profile with this id not found!");
        }

        Member member = singleMember.get();
        String rollNumber = member.getRoll();

        String batchCode = rollNumber.substring(2, 4);

        Optional<UniversitySubject> existingUniversitySubject = Optional.ofNullable(universitySubjectRepository.findBySubjectCode(batchCode));

        if (existingUniversitySubject.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Subject Name with this code not found!");

        }

        UniversitySubject Subject = existingUniversitySubject.get();


        MemberProfileDTO memberProfileDTO = MemberProfileDTO.builder().id(member.getId()).memberName(member.getMemberName() != null ? member.getMemberName() : null).batchName(member.getBatchName() != null ? member.getBatchName() : null).fatherName(member.getFatherName() != null ? member.getFatherName() : null).motherName(member.getMotherName() != null ? member.getMotherName() : null).placeOfBirth(member.getPlaceOfBirth() != null ? member.getPlaceOfBirth() : null).dob(member.getDob() != null ? String.valueOf(member.getDob()) : null).gender(member.getGender() != null ? mapGender(member.getGender()) : null).bloodGroup(member.getBloodGroup() != null ? member.getBloodGroup() : null).phone1(member.getPhone1() != null ? member.getPhone1() : null).phone2(member.getPhone2() != null ? member.getPhone2() : null).mobile(member.getMobile() != null ? member.getMobile() : null).emailId(member.getEmailId() != null ? member.getEmailId() : null).countryCode(member.getCountryCode() != null ? member.getCountryCode() : null).scrollNo(member.getScrollNo() != null ? member.getScrollNo() : null).receiptDate(member.getReceiptDate() != null ? member.getReceiptDate() : null).status(member.getStatus() != null ? member.getStatus() : null).pictureName(member.getPictureName() != null ? member.getPictureName() : null).addUser(member.getAddUser() != null ? member.getAddUser() : null).addDate(member.getAddDate() != null ? member.getAddDate() : null).modUser(member.getModUser() != null ? member.getModUser() : null).modDate(member.getModDate() != null ? member.getModDate() : null).rollNo(member.getRoll() != null ? member.getRoll() : null).universitySubjectId(Subject.getSubjectLongName() != null ? Subject.getSubjectLongName() : null).build();

        MemberDivision memberDivision = member.getMemberDivision();
        if (memberDivision != null) {
            memberProfileDTO.setMemberDivisionId(memberDivision.getFullName());
        } else {
            memberProfileDTO.setMemberDivisionId(null);
        }


        UniversitySubject universitySubject = member.getUniversitySubject();
        if (universitySubject != null) {
            memberProfileDTO.setUniversitySubjectId(universitySubject.getSubjectLongName());
        } else {
            memberProfileDTO.setUniversitySubjectId(null);
        }

        return ResponseEntity.ok(memberProfileDTO);
    }

    @Override
    public ResponseEntity<?> getMemberAllProfile(Integer memberId) {

        Optional<Member> existingMember = Optional.ofNullable(memberRepository.findMemberById(memberId));

        if (existingMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with this id not found!");
        }

        Member member = existingMember.get();

        MemberAllProfileDTO memberAllProfileDTO = new MemberAllProfileDTO();

        memberAllProfileDTO.setMemberId(member.getId());


        String rollNumber = member.getRoll();

        String batchCode = rollNumber.substring(2, 4);

        Optional<UniversitySubject> existingUniversitySubject = Optional.ofNullable(universitySubjectRepository.findBySubjectCode(batchCode));

        if (existingUniversitySubject.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Subject Name with this code not found!");

        }

        UniversitySubject Subject = existingUniversitySubject.get();


        MemberProfileDTO memberProfileDTO = MemberProfileDTO.builder().id(member.getId()).memberName(member.getMemberName() != null ? member.getMemberName() : null).batchName(member.getBatchName() != null ? member.getBatchName() : null).fatherName(member.getFatherName() != null ? member.getFatherName() : null).motherName(member.getMotherName() != null ? member.getMotherName() : null).placeOfBirth(member.getPlaceOfBirth() != null ? member.getPlaceOfBirth() : null).dob(member.getDob() != null ? String.valueOf(member.getDob()) : null).gender(member.getGender() != null ? mapGender(member.getGender()) : null).bloodGroup(member.getBloodGroup() != null ? member.getBloodGroup() : null).phone1(member.getPhone1() != null ? member.getPhone1() : null).phone2(member.getPhone2() != null ? member.getPhone2() : null).mobile(member.getMobile() != null ? member.getMobile() : null).emailId(member.getEmailId() != null ? member.getEmailId() : null).countryCode(member.getCountryCode() != null ? member.getCountryCode() : null).scrollNo(member.getScrollNo() != null ? member.getScrollNo() : null).receiptDate(member.getReceiptDate() != null ? member.getReceiptDate() : null).status(member.getStatus() != null ? member.getStatus() : null).pictureName(member.getPictureName() != null ? member.getPictureName() : null).addUser(member.getAddUser() != null ? member.getAddUser() : null).addDate(member.getAddDate() != null ? member.getAddDate() : null).modUser(member.getModUser() != null ? member.getModUser() : null).modDate(member.getModDate() != null ? member.getModDate() : null).rollNo(member.getRoll() != null ? member.getRoll() : null).universitySubjectId(Subject.getSubjectLongName() != null ? Subject.getSubjectLongName() : null).build();

        MemberDivision memberDivision = member.getMemberDivision();
        if (memberDivision != null) {
            memberProfileDTO.setMemberDivisionId(memberDivision.getFullName());
        } else {
            memberProfileDTO.setMemberDivisionId(null);
        }


        UniversitySubject universitySubject = member.getUniversitySubject();
        if (universitySubject != null) {
            memberProfileDTO.setUniversitySubjectId(universitySubject.getSubjectLongName());
        } else {
            memberProfileDTO.setUniversitySubjectId(null);
        }


        memberAllProfileDTO.setMemberProfileDTO(memberProfileDTO);

        List<MemberAddress> existingMemberAddress = memberAddressRepository.findByMemberId(memberId);

        List<MemberAddressDTOtemp> memberAddressDTOtempList = new ArrayList<>();

        for (MemberAddress memberAddress : existingMemberAddress) {

            MemberAddressDTOtemp memberAddressDTOtemp = MemberAddressDTOtemp.builder().id(memberAddress.getId()).td(memberAddress.getTd()).ed(memberAddress.getEd()).addressType(memberAddress.getAddressType() != null ? memberAddress.getAddressType() : null).addressBook(mapToAddressBook(memberAddress.getAddress())).build();

            memberAddressDTOtempList.add(memberAddressDTOtemp);

        }

        memberAllProfileDTO.setMemberAddressDTOtempList(memberAddressDTOtempList);

        List<MemberEducationInfo> existingMemberEducationInfo = memberEducationInfoRepository.findByMemberId(memberId);

        List<MemberEducationInfoResponseDTO> memberEducationInfoDTOList = new ArrayList<>();

        for (MemberEducationInfo memberEducation : existingMemberEducationInfo) {

            MemberEducationInfoResponseDTO memberEducationInfoResponseDTO = MemberEducationInfoResponseDTO.builder().id(memberEducation.getId() != null ? memberEducation.getId() : null).result(memberEducation.getResult() != null ? memberEducation.getResult() : null).resultType(memberEducation.getResultType() != null ? memberEducation.getResultType().getResultTypeName() : null).degreeType(memberEducation.getDegreeType() != null ? memberEducation.getDegreeType().getDegreeName() : null).boardUniversity(memberEducation.getBoardUniversity() != null ? memberEducation.getBoardUniversity().getBoardUniversity() : null).yearOfPassing(memberEducation.getYearOfPassing() != null ? memberEducation.getYearOfPassing() : null).instituteName(memberEducation.getInstituteName() != null ? memberEducation.getInstituteName() : null).build();

            memberEducationInfoDTOList.add(memberEducationInfoResponseDTO);
        }

        memberAllProfileDTO.setMemberEducationInfoDTOList(memberEducationInfoDTOList);

        List<MemberProfessionalInfo> existingMemberProfessionalInfo = memberProfessionalInfoRepository.findByMemberId(memberId);

        List<MemberProfessionalInfoDTO> memberProfessionalInfoDTOList = new ArrayList<>();

        for (MemberProfessionalInfo memberProfessionalInfo : existingMemberProfessionalInfo) {

            MemberProfessionalInfoDTO memberProfessionalInfoDTO = MemberProfessionalInfoDTO.builder().id(memberProfessionalInfo.getId() != null ? memberProfessionalInfo.getId() : null).companyName(memberProfessionalInfo.getCompanyName() != null ? memberProfessionalInfo.getCompanyName() : null).companyAddress(memberProfessionalInfo.getCompanyAddress() != null ? memberProfessionalInfo.getCompanyAddress() : null).companyType(memberProfessionalInfo.getCompanyType() != null ? memberProfessionalInfo.getCompanyType() : null).designation(memberProfessionalInfo.getDesignation() != null ? memberProfessionalInfo.getDesignation() : null).jd(memberProfessionalInfo.getJd() != null ? memberProfessionalInfo.getJd() : null).fromDate(memberProfessionalInfo.getFromDate() != null ? memberProfessionalInfo.getFromDate() : null).tillDate(memberProfessionalInfo.getTillDate() != null ? memberProfessionalInfo.getTillDate() : null).isPresent(memberProfessionalInfo.getIsPresentJob()).build();

            memberProfessionalInfoDTOList.add(memberProfessionalInfoDTO);
        }

        memberAllProfileDTO.setMemberProfessionalInfoDTOList(memberProfessionalInfoDTOList);


        List<MemberProjectInfo> existingMemberProjectInfo = memberProjectInfoRepository.findByMemberId(memberId);

        List<MemberProjectInfoDTO> memberProjectInfoDTOList = new ArrayList<>();

        for (MemberProjectInfo memberProjectInfo : existingMemberProjectInfo) {

            MemberProjectInfoDTO memberProjectInfoDTO = MemberProjectInfoDTO.builder().id(memberProjectInfo.getId() != null ? memberProjectInfo.getId() : null).memberProjectCategory(memberProjectInfo.getMemberProjectCategory() != null ? memberProjectInfo.getMemberProjectCategory() : null).memberProjectDetails(memberProjectInfo.getMemberProjectDetails() != null ? memberProjectInfo.getMemberProjectDetails() : null).memberProjectMemberRole(memberProjectInfo.getMemberProjectMemberRole() != null ? memberProjectInfo.getMemberProjectMemberRole() : null).memberProjectStartDate(memberProjectInfo.getMemberProjectStartDate() != null ? String.valueOf(memberProjectInfo.getMemberProjectStartDate()) : null).memberProjectEndDate(memberProjectInfo.getMemberProjectEndDate() != null ? String.valueOf(memberProjectInfo.getMemberProjectEndDate()) : null).memberProjectTitle(memberProjectInfo.getMemberProjectTitle() != null ? memberProjectInfo.getMemberProjectTitle() : null).isCurrentlyWorking(memberProjectInfo.getIsCurrentlyWorking()).build();

            memberProjectInfoDTOList.add(memberProjectInfoDTO);
        }

        memberAllProfileDTO.setMemberProjectInfoDTOList(memberProjectInfoDTOList);

        List<MemberCertificate> existingMemberCertificate = memberCertificateRepository.findByMemberId(memberId);


        List<MemberCertificateDTO> memberCertificateDTOList = new ArrayList<>();

        for (MemberCertificate memberCertificate : existingMemberCertificate) {

            MemberCertificateDTO memberCertificateDTO = MemberCertificateDTO.builder().id(memberCertificate.getId() != null ? memberCertificate.getId() : null).certificateShortName(memberCertificate.getCertificateShortName() != null ? memberCertificate.getCertificateShortName() : null).certificateLongName(memberCertificate.getCertificateLongName() != null ? memberCertificate.getCertificateLongName() : null).build();

            memberCertificateDTOList.add(memberCertificateDTO);
        }

        memberAllProfileDTO.setMemberCertificateDTOList(memberCertificateDTOList);


        List<MemberSocialLink> existingMemberSocialLink = memberSocialLinkRepository.findByMemberId(memberId);

        List<MemberSocialLinkProfileDTO> memberSocialLinkProfileDTOList = new ArrayList<>();

        for (MemberSocialLink memberSocialLink : existingMemberSocialLink) {

            MemberSocialLinkProfileDTO memberSocialLinkProfileDTO = MemberSocialLinkProfileDTO.builder().id(memberSocialLink.getId()).link(memberSocialLink.getLink()).linkTypeId(memberSocialLink.getLinkType().getSocialName()).build();

            memberSocialLinkProfileDTOList.add(memberSocialLinkProfileDTO);
        }

        memberAllProfileDTO.setMemberSocialLinkDTOList(memberSocialLinkProfileDTOList);

        List<MemberPublicationInfo> existingMemberPublicationInfo = memberPublicationInfoRepository.findByMemberId(memberId);
        List<MemberPublicationInfoProfileDTO> memberPublicationInfoProfileDTOList = new ArrayList<>();

        for (MemberPublicationInfo memberPublicationInfo : existingMemberPublicationInfo) {

            MemberPublicationInfoProfileDTO memberPublicationInfoProfileDTO = MemberPublicationInfoProfileDTO.builder().publicationUrl(memberPublicationInfo.getPublicationUrl()).publicationVolume(memberPublicationInfo.getPublicationVolume()).publicationMonth(memberPublicationInfo.getPublicationMonth()).publicationYear(memberPublicationInfo.getPublicationYear()).publicationPage(memberPublicationInfo.getPublicationPage()).publicationJournalConference(memberPublicationInfo.getPublicationJournalConference()).publicationTitle(memberPublicationInfo.getPublicationTitle()).publicationAuthor(memberPublicationInfo.getPublicationAuthor()).publicationJournalConferenceName(memberPublicationInfo.getPublicationJournalConferenceName()).publicationPublisher(memberPublicationInfo.getPublicationPublisher()).id(memberPublicationInfo.getId()).build();

            memberPublicationInfoProfileDTOList.add(memberPublicationInfoProfileDTO);

        }
        memberAllProfileDTO.setMemberPublicationInfoProfileDTOList(memberPublicationInfoProfileDTOList);

        return ResponseEntity.ok(memberAllProfileDTO);
    }

    private AddressBook mapToAddressBook(AddressBook address) {
        AddressBook addressBook = new AddressBook();
        // addressBook.setThana(mapToThana(address.getThana()));
        addressBook.setAddress1(address.getAddress1());
        addressBook.setAddress2(address.getAddress2());
        addressBook.setCountryCode(mapToCountry(address.getCountryCode()));
        addressBook.setId(address.getId());
        addressBook.setZipcode(address.getZipcode());
        address.setAddUser(address.getAddUser());
        address.setAddDate(address.getAddDate());
        address.setModUser(address.getModUser());
        address.setModDate(address.getModDate());

        return addressBook;
    }

    private Country mapToCountry(Country ob) {
        Country country = new Country();

        country.setCountryCode(ob.getCountryCode());
        country.setCountryName(ob.getCountryName());
        country.setPrefix(ob.getPrefix());

        return country;

    }

    private Thana mapToThana(Thana ob) {
        Thana thana = new Thana();

        thana.setId(ob.getId());
        thana.setThanaName(ob.getThanaName());
        thana.setDistrict(mapToDistrict(ob.getDistrict()));

        return thana;
    }

    private District mapToDistrict(District ob) {
        District district = new District();
        district.setId(ob.getId());
        district.setDistrictName(ob.getDistrictName());
        district.setDivision(mapToDivision(ob.getDivision()));

        return district;
    }

    private Division mapToDivision(Division ob) {

        Division division = new Division();

        division.setId(ob.getId());
        division.setDivisionName(ob.getDivisionName());

        return division;
    }

    @Override
    public ResponseEntity<InputStreamResource> getMemberPicture(Integer memberId) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Member member = optionalMember.get();

        String gender = "-1";

        if (member.getGender() != null) {
            gender = member.getGender();
        }

        String memberProfileDir = uploadDir + "/member-doc/member-profile";

        try {

            if (member.getPictureName() != null) {
                Path imagePath = Paths.get(memberProfileDir, member.getPictureName());

                // Check if the image file exists
                if (Files.exists(imagePath)) {
                    InputStreamResource resource = new InputStreamResource(new FileInputStream(imagePath.toFile()));

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);

                    return ResponseEntity.ok().headers(headers).body(resource);
                }
            }

            Path dummyImagePath = null;

            if (gender.equals("0") || gender.equals("-1")) {
                dummyImagePath = Paths.get(memberProfileDir, "maleUser.jpg");
            } else {
                dummyImagePath = Paths.get(memberProfileDir, "femaleUser.jpg");
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(dummyImagePath.toFile()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return ResponseEntity.ok().headers(headers).body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<?> getMemberDivisionNameAndYear(String rollNumber) {

        String suffixYear = rollNumber.substring(0, 2);

        String batchCode = rollNumber.substring(2, 4);

        Optional<UniversitySubject> existingUniversitySubject = Optional.ofNullable(universitySubjectRepository.findBySubjectCode(batchCode));

        if (existingUniversitySubject.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Subject Name with this code not found!");

        }

        UniversitySubject universitySubject = existingUniversitySubject.get();


        String fullYear = convertToFullYear(suffixYear);

        Optional<Year> existingYear = Optional.ofNullable(yearRepository.findByYear(fullYear));

        if (existingYear.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Year with this suffix not found!");
        }

        Year findYear = existingYear.get();

        MemberDepartmentAndYearDTO memberDepartmentAndYearDTO = MemberDepartmentAndYearDTO.builder().departmentName(universitySubject.getSubjectLongName()).year(findYear.getYear()).build();

        return ResponseEntity.ok(memberDepartmentAndYearDTO);

    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteMemberById(Integer id) {

        Optional<Member> existingMember = memberRepository.findById(id);

        if (existingMember.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found with this id");
        }

        Optional<Users> user = userRepository.findByMember(existingMember);

        if (user.isPresent()) {

            userRepository.deleteByMemberId(id);
            memberRepository.deleteById(id);
        }

        memberRepository.deleteById(id);

        return ResponseEntity.ok("Member deleted with its id successfully!");
    }

    @Override
    public ResponseEntity<String> addPicture(MemberProfileImageDTO memberProfileImageDTO) throws IOException {
        Optional<Member> member = memberRepository.findById(memberProfileImageDTO.getMemberId());


        if (member.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found with ID: " + memberProfileImageDTO.getMemberId());
        }

        Member memberObj = member.get();

        if (memberProfileImageDTO.getImage().isEmpty()) {
            memberObj.setPictureName(memberObj.getPictureName());
            memberRepository.save(memberObj);

            return ResponseEntity.ok("Picture is added successfully");
        }


        if (!FileUploadUtil.isImageFile(memberProfileImageDTO.getImage())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only image files are allowed.");
        }

        String memberProfileDir = uploadDir + "/member-doc/member-profile";
        String fileName = memberProfileImageDTO.getMemberId() + "_" + memberProfileImageDTO.getImage().getOriginalFilename();
        FileUploadUtil.saveFile(memberProfileDir, fileName, memberProfileImageDTO.getImage());

        memberObj.setPictureName(fileName);

        memberRepository.save(memberObj);

        return ResponseEntity.ok("Picture is added successfully");
    }
//    @Override
//    public List<MemberStatisticDTO> getMemberStatisticsForDate(LocalDate fromDate, LocalDate toDate) {
//        return memberRepository.getMemberStatisticsForDate(fromDate, toDate);
//    }
    @Override
    public List<MemberStatisticDTO> getMemberStatisticsForMonth() {

        return memberRepository.getMemberStatisticsForDate();

    }
//    @Override
//    public Map<ApproveStatus, Long> calculateLastMonthData() {
//        // Use the current date as the input date
//        LocalDateTime currentDate = LocalDateTime.now();
//
//        List<Member> memberList = memberRepository.findAll();
//
//        // Calculate the start date of the previous month
//        LocalDateTime startDateOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
//
//        // Stream through the list of members and filter based on approval status and date range
//        Map<ApproveStatus, Long> result = memberList.stream()
//                .filter(member -> member.getAddDate().isAfter(startDateOfPreviousMonth) && member.getAddDate().isBefore(currentDate))
//                .collect(Collectors.groupingBy(Member::getApproveStatus, Collectors.counting()));
//
//        // If there are no members for a particular status, add it with a count of 0
//        for (ApproveStatus status : ApproveStatus.values()) {
//            result.putIfAbsent(status, 0L);
//        }
//
//        return result;
//    }

}









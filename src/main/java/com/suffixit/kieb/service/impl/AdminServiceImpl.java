package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.async.AsyncMailSender;
import com.suffixit.kieb.config.JWTTokenHelper;
import com.suffixit.kieb.dto.AdminRegisterRequestDTO;
import com.suffixit.kieb.dto.DashboardInfoDTO;
import com.suffixit.kieb.dto.DepartmentDTO;
import com.suffixit.kieb.dto.MembersResponse;
import com.suffixit.kieb.entities.*;
import com.suffixit.kieb.enumerations.ApproveStatus;
import com.suffixit.kieb.model.LoginResponse;
import com.suffixit.kieb.repository.*;
import com.suffixit.kieb.service.AdminService;
import com.suffixit.kieb.utils.GenderUtil;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

import static com.suffixit.kieb.utils.GenderUtil.mapGender;


@Service
@Validated
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final JavaMailSender mailSender;

    private final AsyncMailSender asyncMailSender;

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UniversitySubjectRepository universitySubjectRepository;
    private final JWTTokenHelper jwtTokenHelper;




    public AdminServiceImpl(JavaMailSender mailSender , AsyncMailSender asyncMailSender, MemberRepository memberRepository,
                            UserRepository userRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository ,
                            PasswordEncoder passwordEncoder, UniversitySubjectRepository universitySubjectRepository, JWTTokenHelper jwtTokenHelper) {
        this.mailSender = mailSender;
        this.asyncMailSender = asyncMailSender;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.universitySubjectRepository = universitySubjectRepository;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    public Page<MembersResponse> getAllMembers(
            int page, int size,
            String searchCriteria,
            String subjectId,
            String gender,
            String bloodGroup
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Member> membersPage;

        Specification<Member> combinedSpec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchCriteria != null && !searchCriteria.isEmpty()) {
                ApproveStatus searchStatus = parseApproveStatus(searchCriteria);
                Predicate searchPredicate = criteriaBuilder.like(root.get("memberName"), "%" + searchCriteria + "%");
                searchPredicate = criteriaBuilder.or(
                        searchPredicate,
                        criteriaBuilder.like(root.get("emailId"), "%" + searchCriteria + "%"),
                        criteriaBuilder.like(root.get("bloodGroup"), "%" + searchCriteria + "%"),
                        criteriaBuilder.equal(root.get("approveStatus"), searchStatus),
                        criteriaBuilder.like(root.get("roll"), "%" + searchCriteria + "%")
                );
                predicates.add(searchPredicate);
            }

            if (gender != null && !gender.isEmpty()) {
                Predicate genderPredicate = criteriaBuilder.equal(root.get("gender"), gender);
                predicates.add(genderPredicate);
            }

            if (subjectId != null && !subjectId.isEmpty()) {
                UniversitySubject universitySubject = universitySubjectRepository.findBySubjectCode(subjectId);
                if (universitySubject != null) {
                    Predicate subjectPredicate = criteriaBuilder.equal(root.get("universitySubject"), universitySubject);
                    predicates.add(subjectPredicate);
                }
            }

            if (bloodGroup != null && !bloodGroup.isEmpty()) {
                Predicate genderPredicate = criteriaBuilder.equal(root.get("bloodGroup"), bloodGroup);
                predicates.add(genderPredicate);
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        membersPage = memberRepository.findAll(combinedSpec, pageable);

        if (membersPage.isEmpty()) {
            return Page.empty();
        }

        List<MembersResponse> modelList = new ArrayList<>();
        membersPage.getContent().forEach(member -> {
            MembersResponse response = new MembersResponse();
            response.setId(member.getId());
            response.setMemberName(member.getMemberName() != null ? member.getMemberName() : null);
            response.setEmailId(member.getEmailId() != null ? member.getEmailId() : null);
            response.setRollNo(member.getRoll() != null ? member.getRoll() : null);
            response.setApproveStatus(member.getApproveStatus() != null ? member.getApproveStatus() : null);
            response.setPhoneOne(member.getPhone1() != null ? member.getPhone1() : null);
            response.setBloodGroup(member.getBloodGroup() != null ? member.getBloodGroup() : null);
            response.setGender(member.getGender() != null ? GenderUtil.mapGender(member.getGender()) : null);
            response.setSubjectName(mapSubject(member.getRoll()) != null ? mapSubject(member.getRoll()) : null);

            modelList.add(response);
        });

        return new PageImpl<>(modelList, membersPage.getPageable(), membersPage.getTotalElements());
    }


    private String mapSubject(String roll) {

        if(roll != null && roll.length() >= 7 ){

            String batchCode = roll.substring(2,4);

            Optional<UniversitySubject> existingUniversitySubject = Optional.ofNullable(universitySubjectRepository.findBySubjectCode(batchCode));

            if (existingUniversitySubject.isPresent()) {
                UniversitySubject Subject = existingUniversitySubject.get();
                return Subject.getSubjectLongName();
            }
        }
        return null;

    }

    @Override
    public ResponseEntity<?> getMember(Integer MemberId) {

        Optional<Member> optionalMember = memberRepository.findById(MemberId);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            String rollNumber = member.getRoll();

            String batchCode = rollNumber.substring(2,4);

            Optional<UniversitySubject> existingUniversitySubject = Optional.ofNullable(universitySubjectRepository.findBySubjectCode(batchCode));

            if (existingUniversitySubject.isEmpty()) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Subject Name with this code not found!");

            }

            UniversitySubject Subject = existingUniversitySubject.get();


                MembersResponse membersResponse = MembersResponse.builder()
                        .id(member.getId())
                        .memberName(member.getMemberName())
                        .approveStatus(member.getApproveStatus())
                        .emailId(member.getEmailId())
                        .rollNo(member.getRoll())
                        .phoneOne(member.getPhone1())
                        .bloodGroup(member.getBloodGroup())
                        .gender(mapGender(member.getGender()))
                        .subjectName(Subject.getSubjectLongName())
                        .build();
                return ResponseEntity.ok(membersResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No member found with Id:"+MemberId);
        }


    }

    @Override
    @Transactional
    public ResponseEntity<String> approve(Integer MemberId , String Status) throws Exception {

        Member optionalMember = memberRepository.findById(MemberId)
                .orElseThrow(()->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No member found with Id: " + MemberId));

        String email = optionalMember.getEmailId();

        if(Status.equals("APPROVED")){

            if(!Objects.equals(optionalMember.getApproveStatus(), ApproveStatus.APPROVED)){


                String username = optionalMember.getRoll();
                String password = generateTemporaryPassword();

                String memberName = optionalMember.getMemberName();

                //String message = sendEmail(email , username , password , memberName);

                optionalMember.setApproveStatus(ApproveStatus.APPROVED);
                optionalMember.setApprovedBy("ADMIN");
                optionalMember.setApprovedDate(LocalDateTime.now());

                memberRepository.save(optionalMember);

                Users users = Users
                        .builder()
                        .email(email)
                        .userName(username)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialNonExpired(true)
                        .password(passwordEncoder.encode(password))
                        .enable(true)
                        .member(optionalMember)
                        .build();

                Role role = findRoleByName("MEMBER");

                users.addRoles(role);

                userRepository.save(users);

//                LoginResponse.builder().token(jwtTokenHelper.generateToken(users))
//                        .build();

                log.info("Thread name:"+ Thread.currentThread().getName());
                // sending mail in async way
                asyncMailSender.sendEmail(email , username , password , memberName);

                return ResponseEntity.ok("Member Approved Successfully");

            }
            else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member is approved already");
            }

        }else{
            String memberName = optionalMember.getMemberName();
            String message = declineMember(optionalMember , optionalMember.getApproveStatus() , email , memberName);

            if(message.equals("0")){
                return ResponseEntity.ok("Member declined Successfully");
            }else if(message.equals("1")){
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member is declined already");
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Declined action failed");

            }
        }

    }

    @Transactional
    public String sendEmail(String email, String username, String password, String memberName) throws Exception {
        try {

            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();

            String body = "Dear " + memberName + "\n\n"
                    + "Your account has been created. Below are your login credentials:\n\n"
                    + "Username: " + username + "\n"
                    + "Password: " + password + "\n\n"
                    + "Please log in using these credentials and change your password after the first login.\n\n"
                    + "Thank you,\nYourCompany Support";
            String subject = "Account Created: Your Login Credentials";

            String toEmail = email;

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("kuet1990@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);

            return "Email sent successfully";
        }  catch (AddressException e) {

            System.out.println("Invalid email address: " + e.getMessage());
            throw new BadCredentialsException("Invalid email address");
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Sending email failed");
            throw new BadCredentialsException("Sending email failed");
        }
    }

    @Override
    public Role findRoleByName(String roleName) {

        Role role = roleRepository.findByRole(roleName);

        if (role != null) {
            return roleRepository.findByRole(roleName);
        }
        return null;
    }


    @Override
    @Transactional
    public ResponseEntity<String> triggerMail(Integer MemberId , String ApproveStatus) throws Exception {
        return approve(MemberId , ApproveStatus);
    }


    public String generateTemporaryPassword() {

        int length = 6;

        SecureRandom random = new SecureRandom();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }


    @Override
    public boolean isApproved(Integer Id) {

        Users optionalUser = userRepository.findById(Id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "User not found with id:"+Id));


        if(optionalUser.isEnable()){
            String email = optionalUser.getEmail();

            Member optionalMember = memberRepository.findByEmailId(email).orElseThrow(
                    ()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "Member not found with email:" + email)
            );

            return Objects.equals(optionalMember.getApproveStatus(), ApproveStatus.APPROVED);

        }else{
            return false;
        }


    }

    @Override
    public ResponseEntity<String> addDepartment(DepartmentDTO departmentDTO) {
        if (!departmentDTO.getDepartmentCode().matches("^0\\d{1,}$")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Department code should start with 0 and have a minimum length of 2 characters with numeric digits.");
        }

        if (departmentRepository.existsByDepartmentCodeOrDepartmentName(
                departmentDTO.getDepartmentCode(), departmentDTO.getDepartmentName())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Department with the given code or name already exists.");
        }


        Department department = Department.builder()
                .departmentCode(departmentDTO.getDepartmentCode())
                .departmentName(departmentDTO.getDepartmentName())
                .build();

        departmentRepository.save(department);

        return ResponseEntity.ok("Department has been added");


    }

    @Override
    public String addAdmin(AdminRegisterRequestDTO adminRegisterRequestDTO) {

        String email = adminRegisterRequestDTO.getEmailId();
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String password = adminRegisterRequestDTO.getPassword();

        Users users = Users
                .builder()
                .email(email)
                .userName(username)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialNonExpired(true)
                .password(passwordEncoder.encode(password))
                .enable(true)
                .build();

        Role role = findRoleByName("ADMIN");

        users.addRoles(role);

        userRepository.save(users);

        LoginResponse.builder().token(jwtTokenHelper.generateToken(users))
                .build();

        return "Admin user is created";
    }


    public String declineMember(Member optionalMember , ApproveStatus Status , String email , String memberName) {

        try{

            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();


            if(!Objects.equals(Status, ApproveStatus.DECLINED)){

                optionalMember.setApproveStatus(ApproveStatus.DECLINED);
                memberRepository.save(optionalMember);


                String body = "Dear "+memberName+"\n\n"
                        + "We regret to inform you that your account request has been declined.\n\n"
                        + "If you have any questions or concerns, please contact our support team.\n\n"
                        + "Thank you,\nYourCompany Support";
                String subject = "Account Request Declined";


                String toEmail = email;

                SimpleMailMessage message = new SimpleMailMessage();

                message.setFrom("kuet1990@gmail.com");
                message.setTo(toEmail);
                message.setText(body);
                message.setSubject(subject);
                mailSender.send(message);


                return "0";

               // return "Member declined Successfully";
            }
            else{
                return "1";

              //  return "Member is declined already";
            }

        }catch (Exception e){

            return "-2";
        }

    }

    @Override
    public ResponseEntity<?> dashboardInfo() {
        DashboardInfoDTO dashboardInfoDTO = new DashboardInfoDTO();


        long totalPendingRequests = memberRepository.countByApproveStatus(ApproveStatus.PENDING);
        dashboardInfoDTO.setTotalPendingRequests(totalPendingRequests);


        long totalApprovedRequests = memberRepository.countByApproveStatus(ApproveStatus.APPROVED);
        dashboardInfoDTO.setTotalApprovedRequests(totalApprovedRequests);


        long totalDeclinedRequests = memberRepository.countByApproveStatus(ApproveStatus.DECLINED);
        dashboardInfoDTO.setTotalDeclinedRequests(totalDeclinedRequests);


        dashboardInfoDTO.setTotalMembers(totalApprovedRequests);

        return ResponseEntity.ok(dashboardInfoDTO);
    }

    private ApproveStatus parseApproveStatus(String searchCriteria) {
        try {
            return ApproveStatus.valueOf(searchCriteria);
        } catch (IllegalArgumentException e) {

            return null;
        }
    }

}

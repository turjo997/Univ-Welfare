package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.AdminRegisterRequestDTO;
import com.suffixit.kieb.dto.DepartmentDTO;
import com.suffixit.kieb.dto.MembersResponse;
import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.Role;
import com.suffixit.kieb.enumerations.ApproveStatus;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {
    Page<MembersResponse> getAllMembers(int page, int size, String searchCriteria , String SubjectId ,String Gender , String bloodGroup);

    ResponseEntity<?> getMember(Integer MemberId);

    Role findRoleByName(String roleName);

    ResponseEntity<String> approve(Integer MemberId , String ApproveStatus) throws Exception;

    ResponseEntity<String> triggerMail(Integer MemberId , String ApproveStatus) throws Exception;

    boolean isApproved(Integer Id);

    ResponseEntity<String> addDepartment(DepartmentDTO departmentDTO);

    String addAdmin(AdminRegisterRequestDTO adminRegisterRequestDTO);


    String declineMember(Member optionalMember , ApproveStatus Status , String email , String memberName) throws MessagingException;

    ResponseEntity<?> dashboardInfo();

}

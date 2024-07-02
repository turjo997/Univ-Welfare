package com.suffixit.kieb.controller;


import com.suffixit.kieb.dto.AdminRegisterRequestDTO;
import com.suffixit.kieb.dto.DepartmentDTO;
import com.suffixit.kieb.dto.MemberStatisticDTO;
import com.suffixit.kieb.dto.MembersResponse;
import com.suffixit.kieb.service.AdminService;
import com.suffixit.kieb.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final MemberService memberService;
    public AdminController(AdminService adminService, MemberService memberService) {
        this.adminService = adminService;
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public String registerAdmin(@RequestBody @Valid AdminRegisterRequestDTO adminRegisterRequestDTO) {
        return adminService.addAdmin(adminRegisterRequestDTO);
    }

    @GetMapping("/all/member")
    public Page<MembersResponse> getAllMember
            (@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                          @RequestParam(value = "searchCriteria", required = false) String searchCriteria ,
             @RequestParam(value = "SubjectId" , required = false)String SubjectId ,
             @RequestParam(value = "Gender" , required = false)String Gender ,
             @RequestParam(value = "bloodGroup" , required = false)String bloodGroup

            ) {
        return adminService.getAllMembers(page, size, searchCriteria , SubjectId , Gender , bloodGroup);
    }

    @GetMapping("/get/member")
    public ResponseEntity<?> getMember(@RequestParam Integer MemberId){
        return adminService.getMember(MemberId);
    }

    @PostMapping("/memberApproval")
    public ResponseEntity<String> approvedMember(@RequestParam Integer MemberId , @RequestParam String ApproveStatus) throws Exception {
        return adminService.triggerMail(MemberId , ApproveStatus);
    }

    @GetMapping("/isApprove")
    public boolean isApproved(@RequestParam Integer Id){
        return adminService.isApproved(Id);
    }

    @PostMapping("/add/department")
    public ResponseEntity<String> addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
        return adminService.addDepartment(departmentDTO);
    }

    @GetMapping("/dashboard/info")
    public ResponseEntity<?> dashboardInfo(){
        return adminService.dashboardInfo();
    }

    @GetMapping("/dashboard/info/member-statistics/month")
    public List<MemberStatisticDTO> getMemberStatisticsForMonth(
    ) {
        return memberService.getMemberStatisticsForMonth();
    }

}

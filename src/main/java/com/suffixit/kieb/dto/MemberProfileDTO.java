package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberProfileDTO {

    private Integer id;
    private String memberName;
    private String batchName;
    private String fatherName;
    private String motherName;
    private String placeOfBirth;
    private String dob;
    private String gender;
    private String bloodGroup;
    private String rollNo;
    private String phone1;
    private String phone2;
    private String mobile;
    private String emailId;
    private String countryCode;
    private Integer scrollNo;
    private LocalDate receiptDate;
    private Integer status;
    private String pictureName;
    private String addUser;
    private LocalDateTime addDate;
    private String modUser;
    private LocalDateTime modDate;
    private String memberDivisionId; // For department
    private String universitySubjectId;

}

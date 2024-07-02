package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 100, message = "Name should not exceed 100 characters")
    private String memberName;

    @Size(max = 100, message = "FatherName should not exceed 100 characters")
    private String fatherName;

    @Size(max = 100, message = "Mother Name should not exceed 100 characters")
    private String motherName;

    @Size(max = 100, message = "Birth place should not exceed 100 characters")
    private String placeOfBirth;

    private LocalDate dob;

    private String gender;

    private String bloodGroup;

    //@Pattern(regexp = "/(^(\\+88|0088)?(01){1}[3456789]{1}(\\d){8})$/",
            //message = "Phone number can only contain numeric characters")
    private String phone1;


   // @Pattern(regexp = "/(^(\\+88|0088)?(01){1}[3456789]{1}(\\d){8})$/",
            //message = "Phone number can only contain numeric characters")
    private String phone2;


   // @Pattern(regexp = "/(^(\\+88|0088)?(01){1}[3456789]{1}(\\d){8})$/",
          //  message = "Mobile number can only contain numeric characters")
    private String mobile;

   // @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            //message = "Please provide a valid email address")
    private String emailId;
    private String countryCode;
    private Integer scrollNo;
    private LocalDate receiptDate;
    private Integer status;
    private MultipartFile pictureName;
    private String addUser;
    private LocalDateTime addDate;
    private String modUser;
    private LocalDateTime modDate;

    private Integer universitySubjectId;

    private Integer memberDivisionId; // For department
    private String rollNo;

}

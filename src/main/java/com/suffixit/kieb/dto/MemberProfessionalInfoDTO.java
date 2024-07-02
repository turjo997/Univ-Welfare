package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suffixit.kieb.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberProfessionalInfoDTO {

    private Integer id;
    private Integer memberId;
    private String fromDate;
    private String tillDate;
    private String designation;
    private String companyName;
    private String companyType;
    private String companyAddress;
    private String jd;
    private Boolean isPresent;
}

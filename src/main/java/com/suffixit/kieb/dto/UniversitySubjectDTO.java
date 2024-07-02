package com.suffixit.kieb.dto;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UniversitySubjectDTO {

    private Integer id;

    private String subjectShortName;

    private String subjectLongName;

    private String subjectCode;
//    private String department;
//
//    private Integer universityId;
//
//    private Integer iebDivisionId;
//
//    private Byte status;
//
//    private String addUser;
//
//    private String addDate;
//
//    private String addTerm;
//
//    private String addIp;
//
//    private String modUser;
//
//    private String modDate;
//
//    private String modTerm;
//
//    private String modIp;
}

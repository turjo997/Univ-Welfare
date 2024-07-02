package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberEducationProfileDTO {

    private Integer id;

    private Integer degreeTypeId;

    private String instituteName;

    private Integer boardUniversityId;

    private String yearOfPassing;

    private Integer resultTypeId;

    private String result;
}

package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberEducationInfoResponseDTO {

    private Integer id;

    private String degreeType;

    private String instituteName;

    private String  boardUniversity;

    private String yearOfPassing;

    private String resultType;

    private String result;
}

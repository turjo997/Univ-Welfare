package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suffixit.kieb.entities.Degree;
import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.ResultType;
import com.suffixit.kieb.entities.University;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberEducationInfoDTO {

    private Integer id;

    private Integer degreeTypeId;

    @NotEmpty(message = "Institute Name cannot be empty")
    @Size(max = 100, message = "Institute Name should not exceed 100 characters")
    private String instituteName;

    private Integer boardUniversityId;

    private Integer memberDivisionId;

    @Size(max = 30, message = "Year of passing should not exceed 30 characters")
    private String yearOfPassing;

    private Integer resultTypeId;

    private String result;

}

package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberDepartmentAndYearDTO {

    private String departmentName;

    private String year;
}

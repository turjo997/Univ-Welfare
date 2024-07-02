package com.suffixit.kieb.dto;


import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {

    private Integer id;

    private String departmentCode;

    @NotNull(message = "Department name should not be null")
    private String departmentName;
}

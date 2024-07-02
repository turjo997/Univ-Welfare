package com.suffixit.kieb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEducationInfoListDTO {

    private Integer memberId;

    private List<MemberEducationInfoDTO> educationInfoList;
}

package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberEducationProfileDTOList{

    private Integer memberId;

    private List<MemberEducationProfileDTO> memberEducationProfileDTOList;

}

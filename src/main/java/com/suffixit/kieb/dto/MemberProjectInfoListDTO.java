package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberProjectInfoListDTO {

    private Integer memberId;

    private List<MemberProjectInfoDTO> memberProjectInfoDTOList;
}
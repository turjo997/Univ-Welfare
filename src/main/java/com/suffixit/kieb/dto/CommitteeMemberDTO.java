package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitteeMemberDTO {

    private Integer id;

    private Integer committeeId;

    List<MemberDesignationDTO> memberDesignationDTOList;

    private byte status;

}

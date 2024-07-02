package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberStatisticDTOcopy {


    private Long totalMembers;
    private Long totalPendingMembers;
    private Long totalApprovedMembers;
    private Long totalDeclinedMembers;
}

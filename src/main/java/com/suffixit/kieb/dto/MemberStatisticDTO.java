package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
public interface MemberStatisticDTO {

    String getDate();

    Long getTotalMembers();
    Long getTotalPendingMembers();
    Long getTotalApprovedMembers();
    Long getTotalDeclinedMembers();


}

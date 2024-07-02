package com.suffixit.kieb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardInfoDTO {

    private long totalMembers;
    private long totalPendingRequests;
    private long totalApprovedRequests;
    private long totalDeclinedRequests;
}

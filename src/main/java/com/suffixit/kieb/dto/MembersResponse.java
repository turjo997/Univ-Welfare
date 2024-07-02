package com.suffixit.kieb.dto;


import com.suffixit.kieb.enumerations.ApproveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembersResponse {

    private Integer id;
    private String memberName;
    //private String batchName;
    private String emailId;
    //private LocalDateTime addDate;

    private ApproveStatus approveStatus;
    //private Integer memberDivisionId; // For department
    private String rollNo;
    private String phoneOne;

    private String gender;
    private String bloodGroup;
    private String subjectName;

}

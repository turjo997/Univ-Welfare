package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suffixit.kieb.entities.Member;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberProjectInfoDTO {

    private Integer id;

    private String memberProjectTitle;

    private String memberProjectCategory;

    private String memberProjectMemberRole;

    private String memberProjectDetails;

    private String memberProjectStartDate;

    private String memberProjectEndDate;

    private Boolean isCurrentlyWorking;;

}

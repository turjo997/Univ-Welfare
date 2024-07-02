package com.suffixit.kieb.dto;

import com.suffixit.kieb.entities.CommitteeCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitteeDTO {

    private Integer id;

    private String committeeType;

    private String committeeName;

    private String committeeShortName;

    private String committeeDuration;

    private String committeeDesc;

    private Byte status;

    private Integer showingOrder;

    private String featureImage;

    private Byte published;

    private Integer committeeCategoryId;

    private Integer memberId;

    private String addUser;

    private String addDate;

    private String addTerm;

    private String addIp;

    private String modUser;

    private String modDate;

    private String modTerm;

    private String modIp;
}

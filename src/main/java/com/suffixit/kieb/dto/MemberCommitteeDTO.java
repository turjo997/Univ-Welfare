package com.suffixit.kieb.dto;

import com.suffixit.kieb.entities.Committee;
import com.suffixit.kieb.entities.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCommitteeDTO {

    private Integer id;

    private Integer committeeId;

    private Integer memberId;

    private Integer committeeDesignationId;

    private Integer committeeDepartmentId;

    private Integer committeeShowingOrder;

    private String memberDeatils;

    private String memberShortDeatils;

    private Byte leader;

    private String showingOrder;

    private String featureImage;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private Byte published;

    private String addUser;

    private String addDate;

    private String addTerm;

    private String addIp;

    private String modUser;

    private String modDate;

    private String modTerm;

    private String modIp;
}

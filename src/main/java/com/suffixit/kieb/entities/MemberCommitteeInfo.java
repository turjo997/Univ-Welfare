package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member_committee_info")
public class MemberCommitteeInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "member_committee_title", nullable = false, length = 200)
    private String memberCommitteeTitle;

    @Column(name = "member_committee_institute", nullable = false, length = 200)
    private String memberCommitteeInstitute;

    @Column(name = "member_committee_position", nullable = false, length = 200)
    private String memberCommitteePosition;

    @Column(name = "member_committee_start_date")
    private LocalDate memberCommitteeStartDate;

    @Column(name = "member_committee_end_date")
    private LocalDate memberCommitteeEndDate;

}
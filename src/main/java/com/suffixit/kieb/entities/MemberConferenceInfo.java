package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member_conference_info")
public class MemberConferenceInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "conference_title", nullable = false, length = 200)
    private String conferenceTitle;

    @Column(name = "conference_member_role", nullable = false, length = 200)
    private String conferenceMemberRole;

    @Column(name = "conference_details", nullable = false, length = 200)
    private String conferenceDetails;

    @Column(name = "conference_start_date", nullable = false)
    private LocalDate conferenceStartDate;

    @Column(name = "conference_end_date", nullable = false)
    private LocalDate conferenceEndDate;

    @Column(name = "conference_url", length = 400)
    private String conferenceUrl;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member_project_info")
public class MemberProjectInfo {
    @Id
    @SequenceGenerator(name = "member_project_id_gen", sequenceName = "member_project_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_project_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "member_project_title", nullable = false, length = 200)
    private String memberProjectTitle;

    @Column(name = "member_project_category", nullable = false, length = 200)
    private String memberProjectCategory;

    @Column(name = "member_project_member_role", nullable = false, length = 200)
    private String memberProjectMemberRole;

    @Column(name = "member_project_details", nullable = false, length = 2000)
    private String memberProjectDetails;

    @Column(name = "member_project_start_date")
    private LocalDate memberProjectStartDate;

    @Column(name = "member_project_end_date")
    private LocalDate memberProjectEndDate;

    private Boolean isCurrentlyWorking;

}
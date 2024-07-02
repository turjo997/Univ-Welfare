package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_training_info")
public class MemberTrainingInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "member_training_title", nullable = false, length = 200)
    private String memberTrainingTitle;

    @Column(name = "member_training_institute", nullable = false, length = 200)
    private String memberTrainingInstitute;

    @Column(name = "member_training_course", nullable = false, length = 200)
    private String memberTrainingCourse;

    @Column(name = "member_training_duration", nullable = false, length = 50)
    private String memberTrainingDuration;

    @Column(name = "member_training_year", nullable = false, length = 50)
    private String memberTrainingYear;

}
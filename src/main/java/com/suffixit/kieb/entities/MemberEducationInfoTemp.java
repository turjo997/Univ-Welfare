package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_education_info_temp")
public class MemberEducationInfoTemp {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberTemp member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_type_id")
    private Degree degreeType;

    @Column(name = "institute_name", length = 200)
    private String instituteName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_university_id", nullable = false)
    private University boardUniversity;

    @Column(name = "year_of_passing", length = 30)
    private String yearOfPassing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_type_id")
    private ResultType resultType;

    @Column(name = "result", length = 45)
    private String result;

}
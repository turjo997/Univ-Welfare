package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "member_education_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEducationInfo {

    @Id
    @SequenceGenerator(name = "member_education_info_id_gen", sequenceName = "member_education_info_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_education_info_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_type_id")
    private Degree degreeType;

    @Column(name = "institute_name", length = 200)
    private String instituteName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_university_id")
    private University boardUniversity;

    @JoinColumn(name = "subject")
    private String subject;

    @Column(name = "year_of_passing", length = 30)
    private String yearOfPassing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_type_id")
    private ResultType resultType;

    @Column(name = "result", length =   45)
    private String result;

}
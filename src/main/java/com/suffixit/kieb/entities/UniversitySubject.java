package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "university_subject")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversitySubject {

    @Id
    @SequenceGenerator(name = "member_university_subject_info_id_gen", sequenceName = "member_university_subject_info_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_university_subject_info_id_gen")
    @Column(name = "subject_id", nullable = false)
    private Integer id;

    @Column(name = "subject_short_name", nullable = false, length = 50)
    private String subjectShortName;

    @Column(name = "subject_long_name", nullable = false, length = 300)
    private String subjectLongName;

    @Column(name = "department", length = 200)
    private String department;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "status")
    private Byte status;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "mod_user", length = 45)
    private String modUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "job_post")
public class JobPost {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_POST_CATEGORY_ID")
    private JobPostCategory jobPostCategory;

    @Column(name = "JOB_DESIGNATION", nullable = false, length = 300)
    private String jobDesignation;

    
    @Column(name = "JOB_DESCRIPTION", nullable = false)
    private String jobDescription;

    @Column(name = "JOB_SALARY_MIN", length = 45)
    private String jobSalaryMin;

    @Column(name = "JOB_SALARY_MAX", length = 45)
    private String jobSalaryMax;

    @Column(name = "JOB_SALARY_NEGOTIABLE")
    private Byte jobSalaryNegotiable;

    @Column(name = "JOB_EXPERIENCE", length = 1000)
    private String jobExperience;

    @Column(name = "JOB_AGE", length = 45)
    private String jobAge;

    @Column(name = "GENDER", length = 45)
    private String gender;

    @Column(name = "JOB_TYPE_ID", nullable = false)
    private Integer jobTypeId;

    @Column(name = "COMPANY_ID", nullable = false)
    private Integer companyId;

    @Column(name = "LOCATION", length = 45)
    private String location;

    @Column(name = "APPLICATION_DEADLINE")
    private LocalDateTime applicationDeadline;

    @Column(name = "ADD_DATE", length = 45)
    private String addDate;

    @Column(name = "ADD_USER", nullable = false, length = 45)
    private String addUser;

    @Column(name = "ADD_TERM", length = 45)
    private String addTerm;

    @Column(name = "ADD_IP", length = 45)
    private String addIp;

    @Column(name = "MOD_DATE", length = 45)
    private String modDate;

    @Column(name = "MOD_USER", length = 45)
    private String modUser;

    @Column(name = "MOD_TERM", length = 45)
    private String modTerm;

    @Column(name = "MOD_IP", length = 45)
    private String modIp;

}
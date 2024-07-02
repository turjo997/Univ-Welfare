package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job_post_skill_set")
public class JobPostSkillSet {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "JOB_POST_ID", nullable = false)
    private Integer jobPostId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SKILL_SET_ID", nullable = false)
    private SkillSet skillSet;

    @Column(name = "LEVEL", length = 25)
    private String level;

    @Column(name = "LOCATION", length = 45)
    private String location;

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
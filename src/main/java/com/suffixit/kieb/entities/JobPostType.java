package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job_post_type")
public class JobPostType {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "JOB_TYPE", nullable = false, length = 30)
    private String jobType;

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
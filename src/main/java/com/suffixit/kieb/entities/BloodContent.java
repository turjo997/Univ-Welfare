package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "blood_content")
public class BloodContent {
    @Id
    @Column(name = "id_blood_content", nullable = false)
    private Integer id;

    @Column(name = "blood_content_name", nullable = false, length = 500)
    private String bloodContentName;

    @Column(name = "blood_content_desc", length = 500)
    private String bloodContentDesc;

    @Column(name = "blood_content_mobile", length = 45)
    private String bloodContentMobile;

    @Column(name = "blood_content_address", length = 500)
    private String bloodContentAddress;

    @Column(name = "blood_type", length = 30)
    private String bloodType;

    @Column(name = "boold_req_from", length = 300)
    private String booldReqFrom;

    @Column(name = "blood_req_date")
    private LocalDateTime bloodReqDate;

    @Column(name = "custom_orderby")
    private Integer customOrderby;

    @Column(name = "published", nullable = false)
    private Byte published;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_user", length = 45)
    private String modUser;

}
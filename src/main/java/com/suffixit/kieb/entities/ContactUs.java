package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "contact_us")
public class ContactUs {
    @Id
    @Column(name = "contact_us_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "subject", nullable = false, length = 300)
    private String subject;

    @Column(name = "comments")
    private String comments;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    @Column(name = "ADD_USER", length = 45)
    private String addUser;

    @Column(name = "ADD_DATE")
    private LocalDateTime addDate;

    @Column(name = "ADD_TERM", length = 45)
    private String addTerm;

    @Column(name = "ADD_IP", length = 45)
    private String addIp;

    @Column(name = "MOD_USER", length = 45)
    private String modUser;

    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

    @Column(name = "MOD_TERM", length = 45)
    private String modTerm;

    @Column(name = "MOD_IP", length = 45)
    private String modIp;

}
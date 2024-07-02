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
@Table(name = "subscribers")
public class Subscriber {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "EMAIL_ADDRESS", nullable = false, length = 150)
    private String emailAddress;

    @Column(name = "STATUS", nullable = false)
    private Byte status;

    @Column(name = "ADD_DATE", length = 45)
    private String addDate;

    @Column(name = "ADD_USER", length = 45)
    private String addUser;

    @Column(name = "ADD_IP", length = 45)
    private String addIp;

    @Column(name = "ADD_TERM", length = 45)
    private String addTerm;

    @Column(name = "MOD_DATE", length = 45)
    private String modDate;

    @Column(name = "MOD_USER", length = 45)
    private String modUser;

    @Column(name = "MOD_IP", length = 45)
    private String modIp;

    @Column(name = "MOD_TERM")
    private Integer modTerm;

}
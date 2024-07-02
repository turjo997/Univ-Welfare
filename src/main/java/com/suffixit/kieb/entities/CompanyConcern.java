package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_concern")
public class CompanyConcern {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    @Column(name = "CONCERN_NAME", nullable = false, length = 45)
    private String concernName;

    @Column(name = "ADDRESS_ID", nullable = false)
    private Integer addressId;

    @Column(name = "EMAIL", nullable = false, length = 45)
    private String email;

    @Column(name = "PHONE1", nullable = false, length = 45)
    private String phone1;

    @Column(name = "PHONE2", length = 45)
    private String phone2;

    @Column(name = "WEB", length = 45)
    private String web;

    @Column(name = "CONTACT_PERSON_NAME", nullable = false, length = 100)
    private String contactPersonName;

    @Column(name = "LOGO")
    private byte[] logo;

    @Column(name = "REG_CODE", length = 25)
    private String regCode;

    @Column(name = "VAT_REG_NO", length = 25)
    private String vatRegNo;

    @Column(name = "TIN", length = 25)
    private String tin;

}
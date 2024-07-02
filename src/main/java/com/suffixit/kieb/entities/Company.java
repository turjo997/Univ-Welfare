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
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "COMPANY_NAME", nullable = false, length = 45)
    private String companyName;

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

    @Column(name = "TRANSACTION_START_DATE")
    private LocalDateTime transactionStartDate;

    @Column(name = "TRANSACTION_END_DATE")
    private LocalDateTime transactionEndDate;

    @Column(name = "REG_CODE", length = 25)
    private String regCode;

    @Column(name = "VAT_REG_NO", length = 25)
    private String vatRegNo;

    @Column(name = "TIN", length = 25)
    private String tin;

}
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
@Table(name = "bank")
public class Bank {
    @Id
    @Column(name = "BANK_ID", nullable = false)
    private Integer id;

    @Column(name = "BANK_SHORT_NAME", nullable = false, length = 45)
    private String bankShortName;

    @Column(name = "BANK_NAME", nullable = false, length = 100)
    private String bankName;

}
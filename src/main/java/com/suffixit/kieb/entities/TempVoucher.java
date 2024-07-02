package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_voucher")
public class TempVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pLevel", length = 100)
    private String pLevel;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "Payee", length = 45)
    private String payee;

    @Column(name = "ChequeNo", length = 45)
    private String chequeNo;

    @Column(name = "Debit")
    private Double debit;

    @Column(name = "Credit")
    private Double credit;

}
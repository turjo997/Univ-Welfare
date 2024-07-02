package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_balancesheet")
public class TempBalancesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "AccType", length = 90)
    private String accType;

    @Column(name = "fLevel", length = 90)
    private String fLevel;

    @Column(name = "sLevel", length = 90)
    private String sLevel;

    @Column(name = "PHead", length = 200)
    private String pHead;

    @Column(name = "Amount", length = 45)
    private String amount;

    @Column(name = "Description", length = 130)
    private String description;

    @Column(name = "Total", length = 500)
    private String total;

    @Column(name = "TotalAmount", length = 45)
    private String totalAmount;

}
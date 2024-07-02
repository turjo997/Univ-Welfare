package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_incom_expenditure")
public class TempIncomExpenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fLevel", length = 45)
    private String fLevel;

    @Column(name = "sLevel", length = 145)
    private String sLevel;

    @Column(name = "Notes", length = 45)
    private String notes;

    @Column(name = "amount", length = 45)
    private String amount;

    @Column(name = "total", length = 45)
    private String total;

    @Column(name = "gTotal", length = 45)
    private String gTotal;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "financial_year")
public class FinancialYear {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "financial_year", nullable = false, length = 9)
    private String financialYear;

    @Column(name = "ed", nullable = false)
    private LocalDateTime ed;

    @Column(name = "td", nullable = false)
    private LocalDateTime td;

    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "remarks", length = 450)
    private String remarks;

    @Column(name = "closing_ed", nullable = false)
    private LocalDate closingEd;

    @Column(name = "closing_td", nullable = false)
    private LocalDate closingTd;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "temp_income_statement_ptd")
public class TempIncomeStatementPtd {
    @EmbeddedId
    private TempIncomeStatementPtdId id;

    @Column(name = "JUL_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal julAmount;

    @Column(name = "AUG_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal augAmount;

    @Column(name = "SEP_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal sepAmount;

    @Column(name = "OCT_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal octAmount;

    @Column(name = "NOV_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal novAmount;

    @Column(name = "DEC_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal decAmount;

    @Column(name = "JAN_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal janAmount;

    @Column(name = "FEB_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal febAmount;

    @Column(name = "MAR_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal marAmount;

    @Column(name = "APR_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal aprAmount;

    @Column(name = "MAY_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal mayAmount;

    @Column(name = "JUN_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal junAmount;

    @Column(name = "YTD_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal ytdAmount;

}
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
@Table(name = "temp_trialbalance")
public class TempTrialbalance {
    @EmbeddedId
    private TempTrialbalanceId id;

    @Column(name = "opening_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal openingBalance;

    @Column(name = "debit", nullable = false, precision = 10, scale = 2)
    private BigDecimal debit;

    @Column(name = "credit", nullable = false, precision = 10, scale = 2)
    private BigDecimal credit;

    @Column(name = "closing_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal closingBalance;

}
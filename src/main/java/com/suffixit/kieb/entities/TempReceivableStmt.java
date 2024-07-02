package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "temp_receivable_stmt")
public class TempReceivableStmt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "VOUCHER_ID")
    private Integer voucherId;

    @Column(name = "VOUCHER_TYPE", length = 10)
    private String voucherType;

    @Column(name = "PLEVEL_NAME", length = 500)
    private String plevelName;

    @Column(name = "VOUCHER_DATE")
    private LocalDate voucherDate;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "ACCOUNT_NO", length = 50)
    private String accountNo;

    @Column(name = "DEBIT", precision = 38, scale = 5)
    private BigDecimal debit;

    @Column(name = "CREDIT", precision = 38, scale = 5)
    private BigDecimal credit;

    @Column(name = "BALANCE", precision = 38, scale = 5)
    private BigDecimal balance;

}
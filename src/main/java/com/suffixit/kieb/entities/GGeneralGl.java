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
@Table(name = "g_general_gl")
public class GGeneralGl {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "past_due_day_interval", nullable = false)
    private Integer pastDueDayInterval;

    @Column(name = "accounts_type", nullable = false)
    private Integer accountsType;

    @Column(name = "retained_earning", nullable = false)
    private Integer retainedEarning;

    @Column(name = "profit_loss", nullable = false)
    private Integer profitLoss;

    @Column(name = "exchange_varience_account", nullable = false)
    private Integer exchangeVarienceAccount;

    @Column(name = "bank_charge_account", nullable = false)
    private Integer bankChargeAccount;

    @Column(name = "tax_algorithm", nullable = false)
    private Integer taxAlgorithm;

    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(name = "ed", nullable = false)
    private LocalDateTime ed;

    @Column(name = "td", nullable = false)
    private LocalDateTime td;

    @Column(name = "add_user", nullable = false, length = 45)
    private String addUser;

    @Column(name = "add_date", nullable = false)
    private LocalDateTime addDate;

    @Column(name = "mod_user", length = 45)
    private String modUser;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

}
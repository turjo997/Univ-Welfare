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
@Table(name = "g_fixed_assets")
public class GFixedAsset {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "loss_on_disposal_account", nullable = false)
    private Integer lossOnDisposalAccount;

    @Column(name = "depreciation_period", nullable = false)
    private Integer depreciationPeriod;

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
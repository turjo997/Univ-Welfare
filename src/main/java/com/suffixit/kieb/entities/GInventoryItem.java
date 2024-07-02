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
@Table(name = "g_inventory_items")
public class GInventoryItem {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sales_account", nullable = false)
    private Integer salesAccount;

    @Column(name = "inventory_account", nullable = false)
    private Integer inventoryAccount;

    @Column(name = "cogs_account", nullable = false)
    private Integer cogsAccount;

    @Column(name = "inventory_adjustment_account", nullable = false)
    private Integer inventoryAdjustmentAccount;

    @Column(name = "wip_account", nullable = false)
    private Integer wipAccount;

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
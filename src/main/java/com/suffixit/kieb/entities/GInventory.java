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
@Table(name = "g_inventory")
public class GInventory {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "allow_negative_inventory", nullable = false)
    private Integer allowNegativeInventory;

    @Column(name = "no_zero_amount", nullable = false)
    private Integer noZeroAmount;

    @Column(name = "location_notification", nullable = false)
    private Integer locationNotification;

    @Column(name = "allow_negative_price", nullable = false)
    private Integer allowNegativePrice;

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
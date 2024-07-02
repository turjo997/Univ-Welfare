package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "voucher_hist")
public class VoucherHist {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "voucher_id", nullable = false)
    private Integer voucherId;

    @Column(name = "voucher_type", nullable = false, length = 6)
    private String voucherType;

    @Column(name = "prepared", length = 45)
    private String prepared;

    @Column(name = "checkedby", length = 45)
    private String checkedby;

    @Column(name = "approved", length = 45)
    private String approved;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "voucher_date", nullable = false)
    private LocalDate voucherDate;

    @Column(name = "add_datetime", nullable = false)
    private LocalDateTime addDatetime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "add_user", nullable = false, length = 45)
    private String addUser;

    @Column(name = "add_ip", nullable = false, length = 45)
    private String addIp;

    @Column(name = "mod_datetime")
    private LocalDateTime modDatetime;

    @Column(name = "mod_user", length = 45)
    private String modUser;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

    @Column(name = "del_or_up", nullable = false)
    private Integer delOrUp;

}
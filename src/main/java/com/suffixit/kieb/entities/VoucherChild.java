package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "voucher_child")
public class VoucherChild {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "voucher_id", nullable = false)
    private Integer voucherId;

    @Column(name = "voucher_type", nullable = false, length = 45)
    private String voucherType;

    @Column(name = "voucher_date", nullable = false)
    private LocalDate voucherDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plevel_id", nullable = false)
    private GlCode plevel;

    @Column(name = "account_no", length = 45)
    private String accountNo;

    @Column(name = "payee", length = 45)
    private String payee;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "del_or_up", nullable = false)
    private Integer delOrUp;

    @Column(name = "add_datetime", nullable = false)
    private LocalDateTime addDatetime;

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

}
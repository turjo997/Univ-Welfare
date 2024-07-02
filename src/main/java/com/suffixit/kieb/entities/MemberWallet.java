package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member_wallet")
public class MemberWallet {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "balance", nullable = false, precision = 11, scale = 2)
    private BigDecimal balance;

    @Column(name = "add_date")
    private LocalDate addDate;

    @Column(name = "mod_date")
    private LocalDate modDate;

}
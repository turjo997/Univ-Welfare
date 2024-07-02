package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_fee")
public class MemberFee {
    @Id
    @Column(name = "txn_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "ref_no", nullable = false, length = 150)
    private String refNo;

    @Column(name = "ref_description", nullable = false, length = 500)
    private String refDescription;

    @Column(name = "txn_date", nullable = false)
    private LocalDateTime txnDate;

    @Column(name = "paid_date")
    private LocalDate paidDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fee_year", nullable = false)
    private MemberFeesYear feeYear;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "bill_type", length = 245)
    private String billType;

    @Column(name = "add_user", nullable = false, length = 100)
    private String addUser;

    @Column(name = "add_date", nullable = false)
    private LocalDateTime addDate;

    @Column(name = "mod_user", length = 100)
    private String modUser;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "pay_option")
    private Byte payOption;

}
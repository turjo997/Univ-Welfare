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
@Table(name = "lifemember_age_fee")
public class LifememberAgeFee {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ref_no", nullable = false, length = 150)
    private String refNo;

    @Column(name = "ref_description", nullable = false, length = 500)
    private String refDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_type_id", nullable = false)
    private MemberTypeInfo memberType;

    @Column(name = "age", length = 30)
    private String age;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "txn_date", nullable = false)
    private LocalDateTime txnDate;

    @Column(name = "ed", nullable = false)
    private LocalDate ed;

    @Column(name = "td", nullable = false)
    private LocalDate td;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_payment_mossing")
public class MemberPaymentMossing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "year_14")
    private Integer year14;

    @Column(name = "year_15")
    private Integer year15;

    @Column(name = "year_16")
    private Integer year16;

    @Column(name = "year_17")
    private Integer year17;

    @Column(name = "year_18")
    private Integer year18;

    @Column(name = "year_22")
    private Integer year22;

    @Column(name = "year_23")
    private Integer year23;

    @Column(name = "year_24")
    private Integer year24;

    @Column(name = "year_25")
    private Integer year25;

}
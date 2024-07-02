package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_fee_option")
public class PaymentFeeOption {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "MEMBER_TYPE", length = 10)
    private String memberType;

    @Column(name = "PAYMENT_CODE", length = 10)
    private String paymentCode;

    @Column(name = "PAYMENT_AMOUNT", length = 300)
    private String paymentAmount;

    @Column(name = "ADD_USER", length = 45)
    private String addUser;

    @Column(name = "ADD_DATE", length = 45)
    private String addDate;

    @Column(name = "ADD_TERM", length = 45)
    private String addTerm;

    @Column(name = "ADD_IP", length = 45)
    private String addIp;

    @Column(name = "MOD_USER", length = 45)
    private String modUser;

    @Column(name = "MOD_DATE", length = 45)
    private String modDate;

    @Column(name = "MOD_TERM", length = 45)
    private String modTerm;

    @Column(name = "MOD_IP", length = 45)
    private String modIp;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment_txn_log")
public class PaymentTxnLog {
    @Id
    @Column(name = "TXN_ID", nullable = false, length = 50)
    private String txnId;

    @Column(name = "PAYMENT_TXN_ID", nullable = false, length = 45)
    private String paymentTxnId;

    
    @Column(name = "LOG", nullable = false)
    private String log;

    @Column(name = "DT", nullable = false)
    private LocalDateTime dt;

}
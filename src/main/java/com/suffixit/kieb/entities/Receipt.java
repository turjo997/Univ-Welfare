package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @Column(name = "RECPT_NO", nullable = false, length = 20)
    private String recptNo;

    @Column(name = "RECPT_DATE", nullable = false)
    private LocalDateTime recptDate;

    @Column(name = "CONSUMER_ID", nullable = false)
    private Integer consumerId;

    @Column(name = "INV_NO", nullable = false, length = 50)
    private String invNo;

    @Column(name = "AMOUNT", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "CURRENCY", nullable = false, length = 5)
    private String currency;

    @Column(name = "REC_MODE", nullable = false, length = 3)
    private String recMode;

    @Column(name = "BANK", length = 10)
    private String bank;

    @Column(name = "DOC_REF", length = 10)
    private String docRef;

    @Column(name = "DOC_DATE")
    private LocalDateTime docDate;

    @Column(name = "NARRATION", length = 30)
    private String narration;

    @Column(name = "USER_ID", nullable = false, length = 10)
    private String userId;

    @Column(name = "EXECUTION_DATE", nullable = false)
    private LocalDateTime executionDate;

    @Column(name = "POSTED", nullable = false)
    private Character posted;

    @Column(name = "POST_ID", length = 10)
    private String postId;

    @Column(name = "POST_DATE")
    private LocalDateTime postDate;

    @Column(name = "REVERSED")
    private Character reversed;

    @Column(name = "EXECUTION_TERM", length = 20)
    private String executionTerm;

    @Column(name = "EXECUTION_BRANCH", length = 20)
    private String executionBranch;

    @Column(name = "BATCH_NO", length = 20)
    private String batchNo;

    @Column(name = "REF_01", length = 20)
    private String ref01;

    @Column(name = "REF_02", length = 20)
    private String ref02;

    @Column(name = "REF_03", length = 20)
    private String ref03;

}
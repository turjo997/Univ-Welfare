package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "group_sms_request")
public class GroupSmsRequest {
    @Id
    @Column(name = "REQUEST_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(name = "M_TYPE_ID", length = 30)
    private String mTypeId;

    @Column(name = "M_DIVISION_ID", length = 30)
    private String mDivisionId;

    @Column(name = "M_CENTER_ID", length = 30)
    private String mCenterId;

    @Column(name = "M_SUB_CENTER_ID", length = 30)
    private String mSubCenterId;

    @Column(name = "UNIVERSITY_ID", length = 30)
    private String universityId;

    @Column(name = "TOTAL_SMS_COUNT", nullable = false)
    private Integer totalSmsCount;

    @Column(name = "SMS_BODY", length = 500)
    private String smsBody;

    @Column(name = "SMS_BODY_COUNT", nullable = false)
    private Integer smsBodyCount;

    @Column(name = "PER_SMS_COST", nullable = false, precision = 10, scale = 2)
    private BigDecimal perSmsCost;

    @Column(name = "TOTAL_SMS_COST", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalSmsCost;

    @Column(name = "OLD_SMS_BODY", length = 500)
    private String oldSmsBody;

    @Column(name = "OLD_BODY_COUNT", nullable = false)
    private Integer oldBodyCount;

    @Column(name = "SMS_FROM_NUMBER", length = 100)
    private String smsFromNumber;

    @Column(name = "REF_NO", length = 300)
    private String refNo;

    @Column(name = "REF_DESCRIPTION", length = 500)
    private String refDescription;

    @Column(name = "TXN_DATE", nullable = false)
    private LocalDateTime txnDate;

    @Column(name = "PAID_DATE")
    private LocalDateTime paidDate;

    @Column(name = "PAY_OPTION", nullable = false)
    private Byte payOption;

    @Column(name = "STATUS")
    private Byte status;

    @Column(name = "ADD_DATE")
    private LocalDateTime addDate;

    @Column(name = "ADD_USER", length = 50)
    private String addUser;

    @Column(name = "ADD_TERM", length = 50)
    private String addTerm;

    @Column(name = "ADD_IP", length = 50)
    private String addIp;

    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

    @Column(name = "MOD_USER", length = 50)
    private String modUser;

    @Column(name = "MOD_TERM", length = 50)
    private String modTerm;

    @Column(name = "MOD_IP", length = 50)
    private String modIp;

}
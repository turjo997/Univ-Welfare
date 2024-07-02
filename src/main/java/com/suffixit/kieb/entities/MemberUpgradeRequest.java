package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_upgrade_request")
public class MemberUpgradeRequest {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updgrade_type")
    private MemberTypeInfo updgradeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    @Column(name = "document_org_name", length = 100)
    private String documentOrgName;

    @Column(name = "document_name", length = 100)
    private String documentName;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "approve_date")
    private LocalDateTime approveDate;

    @Column(name = "status")
    private Byte status;

    @Column(name = "ADD_USER")
    private LocalDateTime addUser;

    @Column(name = "ADD_DATE", length = 45)
    private String addDate;

    @Column(name = "ADD_TERM", length = 45)
    private String addTerm;

    @Column(name = "ADD_IP", length = 45)
    private String addIp;

    @Column(name = "MOD_USER", length = 45)
    private String modUser;

    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

    @Column(name = "MOD_TERM", length = 45)
    private String modTerm;

    @Column(name = "MOD_IP", length = 45)
    private String modIp;

}
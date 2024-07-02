package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_document_info_temp")
public class MemberDocumentInfoTemp {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberTemp member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private Document documentType;

    @Column(name = "document_org_name", length = 300)
    private String documentOrgName;

    @Column(name = "document_name", length = 300)
    private String documentName;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_signature")
public class MemberSignature {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "signature_org_name", nullable = false, length = 200)
    private String signatureOrgName;

    @Column(name = "signature_file_name", nullable = false, length = 200)
    private String signatureFileName;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member_professional_info_temp")
public class MemberProfessionalInfoTemp {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberTemp member;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "till_date")
    private LocalDate tillDate;

    @Column(name = "designation", nullable = false, length = 200)
    private String designation;

    @Column(name = "company_name", nullable = false, length = 200)
    private String companyName;

    @Column(name = "company_type", length = 200)
    private String companyType;

    @Column(name = "company_address", length = 450)
    private String companyAddress;

    @Column(name = "jd", length = 300)
    private String jd;

}
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sub_division")
public class SubDivision {
    @Id
    @Column(name = "sub_division_id", nullable = false)
    private Integer id;

    @Column(name = "sub_division_name", nullable = false, length = 200)
    private String subDivisionName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "division_id", nullable = false)
    private MemberDivision division;

    @Column(name = "website", length = 100)
    private String website;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "contact_info", length = 300)
    private String contactInfo;

    @Column(name = "details", length = 500)
    private String details;

}
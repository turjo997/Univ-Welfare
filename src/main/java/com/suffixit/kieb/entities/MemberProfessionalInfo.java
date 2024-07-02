package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_professional_info")
public class MemberProfessionalInfo {
    @Id
    @SequenceGenerator(name = "member_profession_id_gen", sequenceName = "member_profession_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_profession_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "from_date")
    private String fromDate;

    @Column(name = "till_date")
    private String tillDate;

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

    private Boolean isPresentJob;

}
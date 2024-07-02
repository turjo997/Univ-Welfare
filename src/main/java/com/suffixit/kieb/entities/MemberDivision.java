package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member_division")
@Builder
public class MemberDivision {
    @Id
    @SequenceGenerator(name = "member_division_id_gen", sequenceName = "member_division_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_division_id_gen")
    @Column(name = "mem_division_id", nullable = false)
    private Integer id;

    @Column(name = "mem_division_code")
    private String memDivisionCode;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "contact_info", nullable = false, length = 200)
    private String contactInfo;

    @Column(name = "details")
    private String details;

    @Column(name = "featured_picture", length = 100)
    private String featuredPicture;

}
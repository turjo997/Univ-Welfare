package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "member_certificate")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCertificate {
    @Id
    @SequenceGenerator(name = "member_certificate_id_gen", sequenceName = "member_certificate_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_certificate_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "certificate_short_name", nullable = false, length = 200)
    private String certificateShortName;

    @Column(name = "certificate_long_name", nullable = false, length = 200)
    private String certificateLongName;

    @Column(name = "certificate_file_name", nullable = false, length = 200)
    private String certificateFileName;

}
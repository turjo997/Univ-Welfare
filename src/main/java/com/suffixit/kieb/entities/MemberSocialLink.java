package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "member_social_link")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSocialLink {
    @Id
    @SequenceGenerator(name = "member_social_link_id_gen", sequenceName = "member_social_link_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_social_link_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_type_id")
    private SocialLink linkType;

    @Column(name = "link", length = 200)
    private String link;

}
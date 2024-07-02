package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "social_link")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLink {

    @Id
    @SequenceGenerator(name = "social_link_id_gen", sequenceName = "social_link_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "social_link_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "social_name", nullable = false, length = 100)
    private String socialName;

    @Column(name = "social_icon", nullable = false, length = 100)
    private String socialIcon;

}
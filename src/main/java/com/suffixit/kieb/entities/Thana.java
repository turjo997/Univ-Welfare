package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "thana")
public class Thana {
    @Id
    @SequenceGenerator(name = "thana_id_gen", sequenceName = "thana_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thana_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "THANA_NAME", nullable = false, length = 45)
    private String thanaName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DISTRICT_ID", nullable = false)
    private District district;

}
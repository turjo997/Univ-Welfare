package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "district")
public class District {
    @Id
    @SequenceGenerator(name = "district_id_gen", sequenceName = "district_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DISTRICT_NAME", nullable = false, length = 45)
    private String districtName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DIVISION_ID", nullable = false)
    private Division division;

}
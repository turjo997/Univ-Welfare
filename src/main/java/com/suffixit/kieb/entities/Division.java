package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "division")
@Builder
public class Division {
    @Id
    @SequenceGenerator(name = "division_id_gen", sequenceName = "division_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DIVISION_NAME", nullable = false, length = 45)
    private String divisionName;

}
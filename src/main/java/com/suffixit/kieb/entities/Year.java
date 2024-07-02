package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "year")
public class Year {
    @Id
    @SequenceGenerator(name = "year_id_gen", sequenceName = "year_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "year_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    private String year;
}
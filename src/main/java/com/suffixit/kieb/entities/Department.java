package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
@Table(name = "department")

public class Department {

    @Id
    @SequenceGenerator(name = "department_id_gen", sequenceName = "department_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DEPARTMENT_CODE", nullable = false)
    private String departmentCode;


    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 45)
    private String departmentName;

}

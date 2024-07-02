package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "committee_designation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitteeDesignation {


    @Id
    @SequenceGenerator(name = "committee_designation_id_gen", sequenceName = "committee_designation_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "committee_designation_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "designation_name", length = 200)
    private String designationName;

    @Column(name = "short_name", length = 100)
    private String shortName;

    /*@Column(name = "orderby", nullable = true)
    private Integer orderby;*/

}
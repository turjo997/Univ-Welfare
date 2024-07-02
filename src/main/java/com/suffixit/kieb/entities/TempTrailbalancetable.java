package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_trailbalancetable")
public class TempTrailbalancetable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "FHead", length = 100)
    private String fHead;

    @Column(name = "SHead", length = 100)
    private String sHead;

    @Column(name = "Debit")
    private Double debit;

    @Column(name = "Credit")
    private Double credit;

}
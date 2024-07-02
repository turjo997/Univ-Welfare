package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_trailbalance")
public class TempTrailbalance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @Column(name = "fhead", nullable = false, length = 105)
    private String fhead;

    @Column(name = "shead", nullable = false, length = 105)
    private String shead;

    @Column(name = "phead", nullable = false, length = 105)
    private String phead;

    @Column(name = "debit", nullable = false, length = 45)
    private String debit;

    @Column(name = "credit", nullable = false, length = 45)
    private String credit;

}
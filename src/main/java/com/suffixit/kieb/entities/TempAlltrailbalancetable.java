package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_alltrailbalancetable")
public class TempAlltrailbalancetable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "FHead", length = 45)
    private String fHead;

    @Column(name = "SHead", length = 45)
    private String sHead;

    @Column(name = "PHead", length = 45)
    private String pHead;

    @Column(name = "Debit")
    private Double debit;

    @Column(name = "Credit")
    private Double credit;

}
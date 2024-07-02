package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "temp_yearlysummary")
public class TempYearlysummary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uid", length = 45)
    private String uid;

    @Column(name = "name", length = 125)
    private String name;

    @Column(name = "opOwnCon")
    private Double opOwnCon;

    @Column(name = "opOwnPro")
    private Double opOwnPro;

    @Column(name = "opComCon")
    private Double opComCon;

    @Column(name = "opComPro")
    private Double opComPro;

    @Column(name = "ownCon")
    private Double ownCon;

    @Column(name = "comCon")
    private Double comCon;

    @Column(name = "proOwn")
    private Double proOwn;

    @Column(name = "proCom")
    private Double proCom;

    @Column(name = "Loan")
    private Double loan;

}
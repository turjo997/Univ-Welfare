package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_due_txn")
public class MemberDueTxn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "txn")
    private Integer txn;

    //TODO [JPA Buddy] generate columns from DB
}
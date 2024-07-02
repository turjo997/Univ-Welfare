package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member_type")
public class MemberType {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_type_id", nullable = false)
    private MemberTypeInfo memberType;

    @Column(name = "ed", nullable = false)
    private LocalDate ed;

    @Column(name = "td", nullable = false)
    private LocalDate td;

}
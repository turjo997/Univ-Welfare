package com.suffixit.kieb.entities;

import com.suffixit.kieb.enumerations.AddressType;
import jakarta.persistence.*;

import lombok.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member_address")
public class MemberAddress {
    @Id
    @SequenceGenerator(name = "member_address_id_gen", sequenceName = "member_address_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_address_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressBook address;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;


    @Column(name = "ed", nullable = false)
    private LocalDate ed;

    @Column(name = "td", nullable = false)
    private LocalDate td;

}
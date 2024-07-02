package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member_address_temp")
public class MemberAddressTemp {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberTemp member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressBook address;

    @Column(name = "address_type", nullable = false, length = 1)
    private String addressType;

    @Column(name = "ed", nullable = false)
    private LocalDate ed;

    @Column(name = "td", nullable = false)
    private LocalDate td;

}
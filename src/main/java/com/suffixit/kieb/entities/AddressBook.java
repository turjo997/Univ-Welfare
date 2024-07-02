package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "address_book")
public class AddressBook {
    @Id
    @SequenceGenerator(name = "addr_id_gen", sequenceName = "addr_book_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addr_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ADDRESS_1", nullable = false, length = 500)
    private String address1;

    @Column(name = "ADDRESS_2", length = 500)
    private String address2;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "THANA_ID")
//    private Thana thana;

    @Column(name = "ZIPCODE", length = 50)
    private String zipcode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COUNTRY_CODE", nullable = false)
    private Country countryCode;

    @Column(name = "ADD_USER", nullable = false, length = 50)
    private String addUser;

    @Column(name = "ADD_DATE", nullable = false)
    private LocalDateTime addDate;

    @Column(name = "MOD_USER", length = 50)
    private String modUser;

    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

}
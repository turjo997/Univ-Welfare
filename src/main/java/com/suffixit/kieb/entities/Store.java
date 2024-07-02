package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    @Column(name = "STORE_NAME", nullable = false, length = 100)
    private String storeName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_LOCATION", nullable = false)
    private AddressBook storeLocation;

}
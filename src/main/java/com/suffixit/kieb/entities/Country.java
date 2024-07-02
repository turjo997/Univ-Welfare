package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "COUNTRY_CODE", nullable = false, length = 10)
    private String countryCode;

    @Column(name = "COUNTRY_NAME", nullable = false, length = 100)
    private String countryName;

    @Column(name = "PREFIX", nullable = false, length = 10)
    private String prefix;

}
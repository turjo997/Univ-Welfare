package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tax")
public class Tax {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tax", nullable = false, precision = 10, scale = 5)
    private BigDecimal tax;

    @Column(name = "ed", nullable = false)
    private LocalDateTime ed;

    @Column(name = "td", nullable = false)
    private LocalDateTime td;

    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "country_code", nullable = false, length = 45)
    private String countryCode;

}
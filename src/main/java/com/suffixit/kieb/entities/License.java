package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "license")
public class License {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "key_string", nullable = false, length = 245)
    private String keyString;

    @Column(name = "ed", nullable = false)
    private LocalDate ed;

    @Column(name = "td", nullable = false)
    private LocalDate td;

}
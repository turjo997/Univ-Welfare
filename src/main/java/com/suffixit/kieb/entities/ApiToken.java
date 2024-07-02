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
@Table(name = "api_token")
public class ApiToken {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "KEY_STRING", length = 600)
    private String keyString;

    @Column(name = "ED")
    private LocalDate ed;

    @Column(name = "TD")
    private LocalDate td;

}
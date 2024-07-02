package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "village")
public class Village {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "VILLAGE_NAME", length = 45)
    private String villageName;

    @Column(name = "THANA_ID", nullable = false, precision = 10)
    private BigDecimal thanaId;

}
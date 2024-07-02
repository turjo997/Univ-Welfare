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
@Table(name = "degree")
public class Degree {
    @Id
    @Column(name = "degree_id", nullable = false)
    private Integer id;

    @Column(name = "degree_name", nullable = false, length = 100)
    private String degreeName;

}
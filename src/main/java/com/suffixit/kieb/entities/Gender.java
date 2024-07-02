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
@Table(name = "gender")
public class Gender {
    @Id
    @Column(name = "gender_id", nullable = false)
    private Integer id;

    @Column(name = "gender_name", nullable = false, length = 15)
    private String genderName;

}
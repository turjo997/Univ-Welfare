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
@Table(name = "gl_type")
public class GlType {
    @Id
    @Column(name = "GL_TYPE_ID", nullable = false)
    private Integer id;

    @Column(name = "GL_TYPE", nullable = false, length = 100)
    private String glType;

    @Column(name = "ADD_DATE")
    private LocalDate addDate;

    @Column(name = "ADD_USER", nullable = false, length = 100)
    private String addUser;

}
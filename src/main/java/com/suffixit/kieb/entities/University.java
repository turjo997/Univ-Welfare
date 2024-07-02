package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "university")
public class University {
    @Id
    @Column(name = "university_id", nullable = false)
    private Integer id;

    @Column(name = "board_name", nullable = false, length = 200)
    private String boardUniversity;

}
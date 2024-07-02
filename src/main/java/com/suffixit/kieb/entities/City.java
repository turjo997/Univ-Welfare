package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private String id;

    @Column(name = "CITY_SHORT_NAME", nullable = false, length = 10)
    private String cityShortName;

    @Column(name = "CITY_NAME", nullable = false, length = 100)
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STATE_ID", nullable = false)
    private State state;

}
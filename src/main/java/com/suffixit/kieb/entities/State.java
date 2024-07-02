package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state")
public class State {
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private String id;

    @Column(name = "STATE_SHORT_NAME", nullable = false, length = 10)
    private String stateShortName;

    @Column(name = "STATE_NAME", nullable = false, length = 100)
    private String stateName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COUNTRY_CODE", nullable = false)
    private Country countryCode;

}
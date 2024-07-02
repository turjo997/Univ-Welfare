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
@Table(name = "d_timezones")
public class DTimezone {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 44)
    private String name;

    @Column(name = "value", nullable = false, length = 50)
    private String value;

    @Column(name = "timezone", length = 30)
    private String timezone;

}
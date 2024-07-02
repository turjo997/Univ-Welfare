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
@Table(name = "event_slug")
public class EventSlug {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Column(name = "slug_id", nullable = false)
    private Integer slugId;

}
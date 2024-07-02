package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "event_division")
public class EventDivision {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private EventInfo event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID")
    private MemberDivision division;

}
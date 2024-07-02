package com.suffixit.kieb.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "event_news")
public class EventNews {
    @Id
    @SequenceGenerator(name = "eventNews_id_gen", sequenceName = "eventNews_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventNews_id_gen")
    @Column(name = "ID", nullable = false)

    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EventInfo event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NEW_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private NewsInfo newsInfo;

}
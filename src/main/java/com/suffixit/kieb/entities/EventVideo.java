package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "event_video")
public class EventVideo {
    @Id
    @SequenceGenerator(name = "eventVideo_id_gen", sequenceName = "eventVideo_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventVideo_id_gen")

    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private EventInfo event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIDEO_ID")
    private VideoGalleryInfo video;

}
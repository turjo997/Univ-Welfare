package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "album_event")
public class AlbumEvent {
    @Id
    @SequenceGenerator(name = "album_event_id_gen", sequenceName = "album_event_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_event_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALBUM_ID")
    private PictureCategory album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private EventInfo event;

}
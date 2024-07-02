package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "album_slug")
public class AlbumSlug {
    @Id
    @SequenceGenerator(name = "album_slug_id_gen", sequenceName = "album_slug_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_slug_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    private PictureCategory album;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "slug_id", nullable = false)
    private IebSlug slug;

}
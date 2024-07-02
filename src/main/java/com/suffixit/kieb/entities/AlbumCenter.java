package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "album_center")
public class AlbumCenter {
    @Id
    @SequenceGenerator(name = "album_center_id_gen", sequenceName = "album_center_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_center_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private PictureCategory album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

}
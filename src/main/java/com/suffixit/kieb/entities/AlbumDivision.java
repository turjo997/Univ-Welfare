package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "album_division")
public class AlbumDivision {
    @Id
    @SequenceGenerator(name = "album_division_id_gen", sequenceName = "album_division_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_division_id_gen")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private PictureCategory album;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "division_id")
//    private MemberDivision division;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "subject_id")
//    private UniversitySubject subject;

}
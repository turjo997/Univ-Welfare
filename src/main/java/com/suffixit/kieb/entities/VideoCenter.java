package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "video_center")
public class VideoCenter {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIDEO_ID")
    private VideoGalleryInfo video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTER_ID")
    private Center center;

}
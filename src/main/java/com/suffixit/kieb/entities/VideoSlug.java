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
@Table(name = "video_slug")
public class VideoSlug {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "video_id", nullable = false)
    private Integer videoId;

    @Column(name = "slug_id", nullable = false)
    private Integer slugId;

}
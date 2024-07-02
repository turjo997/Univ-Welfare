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
@Table(name = "news_slug")
public class NewsSlug {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "news_id", nullable = false)
    private Integer newsId;

    @Column(name = "slug_id", nullable = false)
    private Integer slugId;

}
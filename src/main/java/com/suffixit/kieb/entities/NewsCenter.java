package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "news_center")
public class NewsCenter {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NEWS_ID")
    private NewsInfo news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTER_ID")
    private Center center;

}
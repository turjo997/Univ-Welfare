package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "news_info")
public class NewsInfo {
    @Id
    @SequenceGenerator(name = "news_info_id_gen", sequenceName = "news_info_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_info_id_gen")
    @Column(name = "id_news", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_category", nullable = false)
    private NewsCategory newsCategory;

//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "news-events",nullable = false)
//    private EventInfo events;


    @Column(name = "news_title", nullable = false , length = 600)
    private String newsTitle;

    @Column(name = "news_slug", length = 300)
    private String newsSlug;

    
    @Column(name = "news_short_desc", nullable = false)
    private String newsShortDesc;

    
    @Column(name = "news_desc")
    private String newsDesc;

    @Column(name = "news_date", length = 50)
    private String newsDate;

    @Column(name = "news_date_time")
    private LocalDateTime newsDateTime;

    @Column(name = "is_sticky", nullable = false)
    private Byte isSticky;

    @Column(name = "showing_order", length = 45)
    private String showingOrder;

    @Column(name = "feature_image", length = 45)
    private String featureImage;

    @Column(name = "image1", length = 45,nullable = true)
    private String image1;

    @Column(name = "image2", length = 45)
    private String image2;

    @Column(name = "image3", length = 45)
    private String image3;

    @Column(name = "image4", length = 45)
    private String image4;

    @Column(name = "published")
    private Byte published;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "mod_user", length = 45)
    private String modUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

}
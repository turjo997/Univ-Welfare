package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "video_gallery_info")
public class VideoGalleryInfo {
    @Id
    @SequenceGenerator(name = "videoGallery_id_gen", sequenceName = "videoGallery_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videoGallery_id_gen")

    @Column(name = "id_video", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "video_category", nullable = false)
    private VideoCategory videoCategory;

    @Column(name = "video_title", nullable = false, length = 1000)
    private String videoTitle;
    
    @Column(name = "video_caption", nullable = false)
    private String videoCaption;

    @Column(name = "video_orginal_link", length = 100)
    private String videoOrginalLink;

    @Column(name = "video_emded_link", length = 100)
    private String videoEmdedLink;

    @Column(name = "showing_order")
    private Integer showingOrder;

    @Column(name = "feature_image", length = 300)
    private String featureImage;

    @Column(name = "image1", length = 300)
    private String image1;

    @Column(name = "image2", length = 300)
    private String image2;

    @Column(name = "image3", length = 300)
    private String image3;

    @Column(name = "image4", length = 300)
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
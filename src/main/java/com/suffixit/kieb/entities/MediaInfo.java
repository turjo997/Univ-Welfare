package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "media_info")
public class MediaInfo {
    @Id
    @Column(name = "id_media", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "media_category", nullable = false)
    private MediaCategory mediaCategory;

    @Column(name = "media_title", nullable = false, length = 2000)
    private String mediaTitle;

    @Column(name = "media_caption", length = 2000)
    private String mediaCaption;

    @Column(name = "media_orginal_name", length = 300)
    private String mediaOrginalName;

    @Column(name = "media_name", length = 300)
    private String mediaName;

    @Column(name = "media_thumb", length = 300)
    private String mediaThumb;

    @Column(name = "showing_order")
    private Integer showingOrder;

    @Column(name = "feature_image", length = 45)
    private String featureImage;

    @Column(name = "image1", length = 45)
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
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
@Table(name = "banner_info")
public class BannerInfo {
    @Id
    @Column(name = "id_banner", nullable = false)
    private Integer id;

    @Column(name = "picture_category")
    private Integer pictureCategory;

    @Column(name = "banner_title", nullable = false, length = 100)
    private String bannerTitle;

    @Column(name = "banner_picture_orginal_name", length = 45)
    private String bannerPictureOrginalName;

    @Column(name = "banner_picture_name", length = 45)
    private String bannerPictureName;

    @Column(name = "banner_picture_thumb", length = 45)
    private String bannerPictureThumb;

    @Column(name = "showing_order", length = 45)
    private String showingOrder;

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
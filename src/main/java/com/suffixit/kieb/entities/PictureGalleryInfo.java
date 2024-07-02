package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "picture_gallery_info")
public class PictureGalleryInfo {
    @Id
    @SequenceGenerator(name = "picture_gallery_id_gen", sequenceName = "picture_gallery_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "picture_gallery_id_gen")
    @Column(name = "id_picture", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "picture_category", nullable = false)
    private PictureCategory pictureCategory;

    @Column(name = "picture_title", nullable = false, length = 2000)
    private String pictureTitle;

    @Column(name = "picture_caption", length = 2000)
    private String pictureCaption;

    @Column(name = "picture_orginal_name", length = 300)
    private String pictureOrginalName;

    @Column(name = "picture_name", length = 300)
    private String pictureName;

    @Column(name = "picture_thumb", length = 300)
    private String pictureThumb;

    @Column(name = "showing_order")
    private Integer showingOrder;

    @Column(name = "feature_image", length = 45)
    private String featureImage;

    @Column(name = "image1", length = 256)
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
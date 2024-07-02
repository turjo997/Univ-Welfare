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
@Table(name = "banner")
public class Banner {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "banner_title", nullable = false, length = 220)
    private String bannerTitle;

    @Column(name = "banner_desc", nullable = false, length = 400)
    private String bannerDesc;

    @Column(name = "showing_order", nullable = false)
    private Integer showingOrder;

    @Column(name = "banner_image", nullable = false)
    private byte[] bannerImage;

    @Column(name = "status", nullable = false)
    private Integer status;

}
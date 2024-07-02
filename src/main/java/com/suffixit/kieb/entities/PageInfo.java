package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "page_info")
public class PageInfo {
    @Id
    @Column(name = "id_page", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_category")
    private PageCategory pageCategory;

    
    @Column(name = "page_title", nullable = false)
    private String pageTitle;

    
    @Column(name = "page_short_desc", nullable = false)
    private String pageShortDesc;

    
    @Column(name = "page_desc")
    private String pageDesc;

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

    @Column(name = "orderby", length = 45)
    private String orderby;

}
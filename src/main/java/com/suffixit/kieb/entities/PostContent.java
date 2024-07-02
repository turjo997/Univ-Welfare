package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post_content")
public class PostContent {
    @Id
    @Column(name = "id_post_content", nullable = false)
    private Integer id;

    @Column(name = "content_category", length = 300)
    private String contentCategory;

    @Column(name = "post_content_type", nullable = false, length = 30)
    private String postContentType;

    @Column(name = "post_content_title", nullable = false, length = 500)
    private String postContentTitle;

    @Column(name = "content_short_desc", length = 500)
    private String contentShortDesc;

    
    @Column(name = "content_desc")
    private String contentDesc;

    @Column(name = "post_link", length = 300)
    private String postLink;

    @Column(name = "content_date")
    private LocalDateTime contentDate;

    @Column(name = "video_name", length = 300)
    private String videoName;

    @Column(name = "video_link", length = 300)
    private String videoLink;

    @Column(name = "video_views")
    private Integer videoViews;

    @Column(name = "videoOpt")
    private Byte videoOpt;

    @Column(name = "picture", length = 300)
    private String picture;

    @Column(name = "`like`")
    private Integer like;

    @Column(name = "share")
    private Integer share;

    @Column(name = "content_owner", length = 100)
    private String contentOwner;

    @Column(name = "custom_orderby")
    private Integer customOrderby;

    @Column(name = "published", nullable = false)
    private Byte published;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_user", length = 45)
    private String modUser;

}
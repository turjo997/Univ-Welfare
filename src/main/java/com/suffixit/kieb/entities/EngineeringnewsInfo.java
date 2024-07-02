package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "engineeringnews_info")
public class EngineeringnewsInfo {
    @Id
    @Column(name = "id_engineeringnews", nullable = false)
    private Integer id;

    @Column(name = "engineeringnewsOfficeId", length = 100)
    private String engineeringnewsOfficeId;

    @Column(name = "engineeringnews_title", nullable = false, length = 500)
    private String engineeringnewsTitle;

    
    @Column(name = "engineeringnews_desc")
    private String engineeringnewsDesc;

    @Column(name = "cover_page_picture", length = 100)
    private String coverPagePicture;

    @Column(name = "cover_page_thumb", length = 100)
    private String coverPageThumb;

    @Column(name = "engineeringnews_link", length = 300)
    private String engineeringnewsLink;

    @Column(name = "engineeringnews_date")
    private LocalDateTime engineeringnewsDate;

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
package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post_notice")
public class PostNotice {
    @Id
    @Column(name = "id_notice_content", nullable = false)
    private Integer id;

    @Column(name = "noticeOfficeId", length = 100)
    private String noticeOfficeId;

    @Column(name = "notice_category", length = 45)
    private String noticeCategory;

    @Column(name = "notice_content_type", length = 30)
    private String noticeContentType;

    @Column(name = "notice_content_title", nullable = false, length = 500)
    private String noticeContentTitle;

    @Column(name = "notice_short_desc", length = 500)
    private String noticeShortDesc;

    
    @Column(name = "notice_desc")
    private String noticeDesc;

    @Column(name = "notice_link", length = 300)
    private String noticeLink;

    @Column(name = "notice_date")
    private LocalDateTime noticeDate;

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